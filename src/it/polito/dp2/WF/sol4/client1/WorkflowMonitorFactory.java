package it.polito.dp2.WF.sol4.client1;


import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowMonitorException;
import it.polito.dp2.WF.sol4.client1.workflowmonitor.WorkflowMonitorImpl;

import java.net.MalformedURLException;

public class WorkflowMonitorFactory extends it.polito.dp2.WF.WorkflowMonitorFactory {

    @Override
    public WorkflowMonitor newWorkflowMonitor() throws WorkflowMonitorException {
        try {
            return new WorkflowMonitorImpl();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
