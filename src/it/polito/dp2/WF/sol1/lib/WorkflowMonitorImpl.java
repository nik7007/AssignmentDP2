package it.polito.dp2.WF.sol1.lib;


import it.polito.dp2.WF.*;
import it.polito.dp2.WF.sol1.reference.DateFormat;
import it.polito.dp2.WF.sol1.reference.XMLFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WorkflowMonitorImpl implements WorkflowMonitor {

    private Map<String, WorkflowReader> workflowReaderMap;
    private Map<Calendar, ProcessReader> processReaderMap;

    public WorkflowMonitorImpl() {
        workflowReaderMap = new HashMap<>();
        processReaderMap = new HashMap<>();
        String fileName = System.getProperty("it.polito.dp2.WF.sol1.WFInfo.file");
        //System.err.println(fileName);
        parseXMLWithDOM(fileName);
    }

    private void parseXMLWithDOM(String xmlFile) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            createElements(document);
        } catch (ParserConfigurationException | SerializerException | WorkflowReaderException | WorkflowMonitorException | ParseException | IOException | SAXException e) {
            e.printStackTrace();
        }


    }

    private void createElements(Document document) throws WorkflowMonitorException, ParseException, WorkflowReaderException, SerializerException {

        Element root = document.getDocumentElement();

        NodeList workflows = root.getElementsByTagName(XMLFormat.ELEM_WORKFLOW.toString());
        NodeList processes = root.getElementsByTagName(XMLFormat.ELEM_PROCESS.toString());

        for (int i = 0; i < workflows.getLength(); i++) {
            Node node = workflows.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
                addWorkflowReader(createWorkflowReader((Element) node));
        }

        for (int i = 0; i < workflows.getLength(); i++) {
            Node node = workflows.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element workflowElement = (Element) node;
                WorkflowReaderImp workflow = (WorkflowReaderImp) workflowReaderMap.get(workflowElement.getAttribute(XMLFormat.ATT_NAME.toString()));

                NodeList simpleActionElements = workflowElement.getElementsByTagName(XMLFormat.ELEM_SIMPLE_ACTION.toString());
                NodeList processActionsElements = workflowElement.getElementsByTagName(XMLFormat.ELEM_PROCESS_ACTION.toString());

                for (int j = 0; j < processActionsElements.getLength(); j++) {
                    Node procNode = workflows.item(j);

                    if (procNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element procElem = (Element) procNode;

                        workflow.addActionReader(createAction(procElem, workflow));
                    }
                }

                createSimpleActionReader(workflow, simpleActionElements);

            }

        }

        for (int i = 0; i < processes.getLength(); i++) {
            Node node = processes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
                addProcessReader(createProcessReader((Element) node));
        }

    }

    private ActionReader createAction(Element action, WorkflowReader workflow) throws SerializerException {

        String name = action.getAttribute(XMLFormat.ATT_NAME.toString());
        String role = action.getAttribute(XMLFormat.ATT_ROLE.toString());
        boolean auto = Boolean.valueOf(action.getAttribute(XMLFormat.ATT_AUTO.toString()));

        if (action.getTagName().equals(XMLFormat.ELEM_SIMPLE_ACTION.toString())) {

            return new SimpleActionReaderImp(name, workflow, role, auto);

        } else if (action.getTagName().equals(XMLFormat.ELEM_PROCESS_ACTION.toString())) {

            WorkflowReader workflowReader = workflowReaderMap.get(action.getAttribute(XMLFormat.ATT_SUB_WORKFLOW.toString()));

            return new ProcessActionReaderImp(name, workflow, role, auto, workflowReader);

        } else {
            throw new SerializerException("\'" + action.getTagName() + "\' is not a valid tag name!");
        }
        //return null;
    }

    private void createSimpleActionReader(WorkflowReaderImp workflow, NodeList simpleActionElements) throws SerializerException, WorkflowReaderException {

        Map<String, ActionReader> actionReaderMap = new HashMap<>();
        Map<String, List<String>> simpleNextActions = new HashMap<>();

        for (ActionReader ar : workflow.getActions())
            actionReaderMap.put(ar.getName(), ar);

        for (int i = 0; i < simpleActionElements.getLength(); i++) {

            Node sActionNode = simpleActionElements.item(i);
            {
                if (sActionNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element sActionElem = (Element) sActionNode;


                    ActionReader simpleActionReaderImp = createAction(sActionElem, workflow);
                    String actionName = simpleActionReaderImp.getName();
                    actionReaderMap.put(actionName, simpleActionReaderImp);

                    workflow.addActionReader(simpleActionReaderImp);

                    if (sActionElem.hasChildNodes()) {
                        List<String> simpleNextActon = new LinkedList<>();
                        NodeList subActions = sActionElem.getElementsByTagName(XMLFormat.ELEM_SUB_ACTION.toString());
                        for (int j = 0; j < subActions.getLength(); j++) {
                            Node subActionNode = subActions.item(j);

                            if (subActionNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element subActionElem = (Element) subActionNode;
                                simpleNextActon.add(subActionElem.getAttribute(XMLFormat.ATT_NAME_REF.toString()));

                            }

                        }

                        simpleNextActions.put(actionName, simpleNextActon);

                    }

                }
            }


        }

        /*simpleNextActions.forEach((actionKey, actionList) -> {

            ActionReader actionReader = actionReaderMap.get(actionKey);
            if (actionReader instanceof SimpleActionReaderImp) {
                for (String action : actionList) {
                    ActionReader actionR = actionReaderMap.get(action);
                    if (actionR != null)
                        ((SimpleActionReaderImp) actionReader).addActionReader(actionR);
                }
            }

        });*/

        for (Map.Entry<String, List<String>> entry : simpleNextActions.entrySet()) {
            String actionKey = entry.getKey();
            List<String> actionList = entry.getValue();

            ActionReader actionReader = actionReaderMap.get(actionKey);
            if (actionReader instanceof SimpleActionReaderImp) {
                for (String action : actionList) {
                    ActionReader actionR = actionReaderMap.get(action);
                    if (actionR != null)
                        ((SimpleActionReaderImp) actionReader).addActionReader(actionR);
                    else
                        throw new WorkflowReaderException("\'" + action + "\' does not exit");
                }
            }
        }


    }

    private WorkflowReader createWorkflowReader(Element element) {
        return new WorkflowReaderImp(element.getAttribute(XMLFormat.ATT_NAME.toString()));

    }

    private Calendar createCalendar(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.DATE_FORMAT.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(date));

        return calendar;

    }

    private ProcessReader createProcessReader(Element element) throws ParseException, WorkflowReaderException {

        String date = element.getAttribute(XMLFormat.ATT_DATE.toString());
        Calendar calendar = createCalendar(date);
        WorkflowReader workflow = getWorkflow(element.getAttribute(XMLFormat.ELEM_WORKFLOW.toString()));
        NodeList actionStatus = element.getElementsByTagName(XMLFormat.ELEM_ACTION_STATUS.toString());
        ProcessReaderImp processReader = new ProcessReaderImp(calendar, workflow);

        for (int i = 0; i < actionStatus.getLength(); i++) {
            Node node = actionStatus.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
                processReader.addActionStatusReader(createActionStatusReader((Element) node));
        }


        return processReader;
    }

    private ActionStatusReader createActionStatusReader(Element element) throws ParseException {

        ActionStatusReaderImp actionStatusReader = new ActionStatusReaderImp(element.getAttribute(XMLFormat.ATT_NAME.toString()));
        Actor actor = null;
        Calendar date = null;

        if (element.hasAttribute(XMLFormat.ATT_DATE.toString()))
            date = createCalendar(element.getAttribute(XMLFormat.ATT_DATE.toString()));

        actionStatusReader.terminate(date);

        if (element.hasChildNodes()) {
            NodeList actorsNode = element.getChildNodes();
            for (int i = 0; i < actorsNode.getLength(); i++) {
                Node node = actorsNode.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    actor = createActor((Element) node);
                    break;
                }
            }
        }
        actionStatusReader.takeInCharge(actor);
        return actionStatusReader;
    }

    private Actor createActor(Element element) {

        return new Actor(element.getAttribute(XMLFormat.ATT_NAME.toString()), element.getAttribute(XMLFormat.ATT_ROLE.toString()));
    }

    public void addWorkflowReader(WorkflowReader workflowReader) throws WorkflowMonitorException {
        String name = workflowReader.getName();
        if (workflowReaderMap.containsKey(name))
            throw new WorkflowMonitorException("This WorkflowReader \'" + name + "\' is already present!");
        else workflowReaderMap.put(name, workflowReader);
    }

    public void addProcessReader(ProcessReader processReader) throws WorkflowMonitorException, WorkflowReaderException {

        Calendar date = processReader.getStartTime();

        if (processReaderMap.containsKey(date))
            throw new WorkflowMonitorException("This ProcessReader \'" + date.toString() + "\' is already present!");
        else {
            processReaderMap.put(date, processReader);

            /*WorkflowReaderImp workflowReader = (WorkflowReaderImp) workflowReaderMap.get(processReader.getWorkflow().getName());
            if (workflowReader == null)
                throw new WorkflowMonitorException("Impossible to find " + processReader.getWorkflow().getName());
            else workflowReader.addProcessReader(processReader);*/
        }


    }


    @Override
    public Set<WorkflowReader> getWorkflows() {
        return new HashSet<>(workflowReaderMap.values());
    }

    @Override
    public WorkflowReader getWorkflow(String s) {

        if (workflowReaderMap.containsKey(s))
            return workflowReaderMap.get(s);
        else
            return null;
    }

    @Override
    public Set<ProcessReader> getProcesses() {
        return new HashSet<>(processReaderMap.values());
    }
}
