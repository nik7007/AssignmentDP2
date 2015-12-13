package it.polito.dp2.WF.sol3;


import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowMonitorException;
import it.polito.dp2.WF.sol3.lib.WorkflowMonitorImpl;

public class WorkflowMonitorFactory extends it.polito.dp2.WF.WorkflowMonitorFactory{
    @Override
    public WorkflowMonitor newWorkflowMonitor() throws WorkflowMonitorException {
        return new WorkflowMonitorImpl();
    }
}
