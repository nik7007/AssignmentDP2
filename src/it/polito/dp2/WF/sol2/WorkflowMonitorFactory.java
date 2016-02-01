package it.polito.dp2.WF.sol2;

import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowMonitorException;
import it.polito.dp2.WF.sol2.lib.WorkflowMonitorImpl;
import it.polito.dp2.WF.sol2.lib.WorkflowReaderException;

public class WorkflowMonitorFactory extends
		it.polito.dp2.WF.WorkflowMonitorFactory {
	@Override
	public WorkflowMonitor newWorkflowMonitor() throws WorkflowMonitorException {
		try {
			return new WorkflowMonitorImpl();
		} catch (WorkflowReaderException e) {
			// e.printStackTrace();
			throw new WorkflowMonitorException();
		}
	}
}
