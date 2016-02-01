package it.polito.dp2.WF.sol4.client1;


import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowMonitorException;
import it.polito.dp2.WF.lab4.gen.GetInfoProcessNameFault_Exception;
import it.polito.dp2.WF.lab4.gen.GetInfoWorkflowFault_Exception;
import it.polito.dp2.WF.sol4.client1.workflowmonitor.WorkflowMonitorImpl;
import it.polito.dp2.WF.sol4.client1.workflowmonitor.WorkflowReaderException;

import java.net.MalformedURLException;

public class WorkflowMonitorFactory extends it.polito.dp2.WF.WorkflowMonitorFactory {

    @Override
    public WorkflowMonitor newWorkflowMonitor() throws WorkflowMonitorException {
        try {
            return new WorkflowMonitorImpl();
        } catch (MalformedURLException | GetInfoWorkflowFault_Exception | GetInfoProcessNameFault_Exception | WorkflowReaderException e) {
            //e.printStackTrace();
        	throw new WorkflowMonitorException();
        }
       // return null;
    }
}
