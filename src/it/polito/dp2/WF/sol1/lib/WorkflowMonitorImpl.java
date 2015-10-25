package it.polito.dp2.WF.sol1.lib;


import it.polito.dp2.WF.ProcessReader;
import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowMonitorException;
import it.polito.dp2.WF.WorkflowReader;

import java.util.*;

public class WorkflowMonitorImpl implements WorkflowMonitor {

    private Map<String, WorkflowReader> workflowReaderMap;
    private Map<Calendar, ProcessReader> processReaderMap;

    public WorkflowMonitorImpl() {
        workflowReaderMap = new HashMap<>();
        processReaderMap = new HashMap<>();
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
