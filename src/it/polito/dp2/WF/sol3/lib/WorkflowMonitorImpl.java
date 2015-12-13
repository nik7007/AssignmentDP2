package it.polito.dp2.WF.sol3.lib;


import it.polito.dp2.WF.ProcessReader;
import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowReader;
import it.polito.dp2.WF.lab3.Refreshable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WorkflowMonitorImpl implements WorkflowMonitor, Refreshable {

    private Map<String, WorkflowReader> workflowReaderMap;
    //private Map<Calendar, ProcessReader> processReaderMap;


    public WorkflowMonitorImpl() {
        workflowReaderMap = new HashMap<>();
        //processReaderMap = new HashMap<>();
        String url = System.getProperty("it.polito.dp2.WF.sol3.URL");
        //System.err.println(url);


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
        return new HashSet<>();
    }

    @Override
    public void refresh() {

    }
}
