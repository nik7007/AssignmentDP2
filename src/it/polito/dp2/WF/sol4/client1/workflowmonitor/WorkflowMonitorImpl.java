package it.polito.dp2.WF.sol4.client1.workflowmonitor;


import it.polito.dp2.WF.ProcessReader;
import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowReader;
import it.polito.dp2.WF.lab4.gen.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WorkflowMonitorImpl implements WorkflowMonitor {

    private WorkflowInfoInterface proxy;
    private List<String> workflowNames = null;

    public WorkflowMonitorImpl() throws MalformedURLException {

        String url = System.getProperty("it.polito.dp2.WF.lab4.URL");
        if (url == null)
            url = "http://localhost:7071/wfinfo";

        WorkflowService service = new WorkflowService(new URL(url));
        proxy = service.getWorkflowInfo();
    }

    private void getWorkflowNames() {
        GetWorkflowNameResponseType result = proxy.getInfoWorkflowName(new GetWorkflowName());
        workflowNames = result.getWorkflowName();
    }

    private void generatedWorkflow() throws GetInfoWorkflowFault_Exception {

        getWorkflowNames();
        GetWorkflowByName names = new GetWorkflowByName();
        names.getWorkflowName().addAll(workflowNames);
        List<WorkflowType> wfList = new LinkedList<>();
        for (GetWorkflow.LastModTimeAndWorkflow wf : proxy.getInfoWorkflow(names).getLastModTimeAndWorkflow()) {
            wfList.add(wf.getWorkflow());
        }
    }

    @Override
    public Set<WorkflowReader> getWorkflows() {
        return null;
    }

    @Override
    public WorkflowReader getWorkflow(String s) {
        return null;
    }

    @Override
    public Set<ProcessReader> getProcesses() {
        return null;
    }
}