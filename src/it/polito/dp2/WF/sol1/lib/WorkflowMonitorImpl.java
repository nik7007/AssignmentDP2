package it.polito.dp2.WF.sol1.lib;


import it.polito.dp2.WF.ProcessReader;
import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowMonitorException;
import it.polito.dp2.WF.WorkflowReader;
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
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void createElements(Document document) throws WorkflowMonitorException {

        Element root = document.getDocumentElement();

        NodeList workflows = root.getElementsByTagName(XMLFormat.ELEM_WORKFLOW.toString());
        NodeList processes = root.getElementsByTagName(XMLFormat.ELEM_PROCESS.toString());

        for (int i = 0; i < workflows.getLength(); i++) {
            Node node = workflows.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
                addWorkflowReader(createWorkflowReader((Element) node));
        }

        for (int i =0;i<processes.getLength();i++)
        {
            Node node = processes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE)
                //ToDO:
            ;
        }

    }

    private WorkflowReader createWorkflowReader(Element element) {
        return new WorkflowReaderImp(element.getAttribute(XMLFormat.ATT_NAME.toString()));

    }

    private ProcessReader createProcessReader(Element element)
    {
        String date = element.getAttribute(XMLFormat.ATT_DATE.toString());

        return null; //new ProcessReaderImp()
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
