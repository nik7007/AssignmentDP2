package it.polito.dp2.WF.sol3;


import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowMonitorException;
import it.polito.dp2.WF.sol3.lib.WorkflowMonitorImpl;

import java.net.MalformedURLException;

public class WorkflowMonitorFactory extends it.polito.dp2.WF.WorkflowMonitorFactory{
    @Override
    public WorkflowMonitor newWorkflowMonitor() throws WorkflowMonitorException {
        try {
            return new WorkflowMonitorImpl();
        } catch (MalformedURLException e) {
        	throw new WorkflowMonitorException();
        }
    }
}
