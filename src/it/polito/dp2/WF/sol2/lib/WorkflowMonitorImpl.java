package it.polito.dp2.WF.sol2.lib;


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
        String fileName = System.getProperty("it.polito.dp2.WF.sol2.WFInfo.file");
        //System.err.println(fileName);
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
