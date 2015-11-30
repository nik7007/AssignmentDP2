package it.polito.dp2.WF.sol1.lib;


import it.polito.dp2.WF.*;
import it.polito.dp2.WF.sol1.reference.DateFormat;
import it.polito.dp2.WF.sol1.reference.XMLFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
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

    private String fileName;
    private Document document;

    public WorkflowMonitorImpl() {
        workflowReaderMap = new HashMap<>();
        processReaderMap = new HashMap<>();
        fileName = System.getProperty("it.polito.dp2.WF.sol1.WFInfo.file");
        parseXMLWithDOM(fileName);
    }

    private void parseXMLWithDOM(String xmlFile) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(xmlFile));
            createElements(document);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (WorkflowMonitorException e) {
            e.printStackTrace();
        } catch (WorkflowReaderException e) {
            e.printStackTrace();
        }


    }

    private void createElements(Document document) throws WorkflowMonitorException, ParseException, WorkflowReaderException {

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
                //ToDo: actions!!!
                Element workflowElement = (Element)node;
                NodeList simpleActions = workflowElement.getElementsByTagName(XMLFormat.ELEM_SIMPLE_ACTION.toString());

            }

        }

        for (int i = 0; i < processes.getLength(); i++) {
            Node node = processes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
                addProcessReader(createProcessReader((Element) node));
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
            Node node = element.getFirstChild();
            if (node.getNodeType() == Node.ELEMENT_NODE)
                actor = createActor((Element) node);
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
