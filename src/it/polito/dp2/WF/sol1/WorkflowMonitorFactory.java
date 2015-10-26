package it.polito.dp2.WF.sol1;


import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowMonitorException;
import it.polito.dp2.WF.sol1.lib.WorkflowMonitorImpl;

public class WorkflowMonitorFactory extends it.polito.dp2.WF.WorkflowMonitorFactory {

    public WorkflowMonitorFactory() {
        super();
    }


    @Override
    public WorkflowMonitor newWorkflowMonitor() throws WorkflowMonitorException {
        return new WorkflowMonitorImpl();
    }
}
