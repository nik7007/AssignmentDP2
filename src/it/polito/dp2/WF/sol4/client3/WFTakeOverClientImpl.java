package it.polito.dp2.WF.sol4.client3;

import java.net.MalformedURLException;
import java.net.URL;

import it.polito.dp2.WF.lab4.ServiceUnavailableException;
import it.polito.dp2.WF.lab4.WFTakeOverClient;
import it.polito.dp2.WF.lab4.gen.ActionInProcessType;
import it.polito.dp2.WF.lab4.gen.TakeOverActionFault;
import it.polito.dp2.WF.lab4.gen.WorkflowControlInterface;
import it.polito.dp2.WF.lab4.gen.WorkflowService;

public class WFTakeOverClientImpl implements WFTakeOverClient {
	
	private static String URL = "http://localhost:7070/wfcontrol";

	@Override
	public boolean takeOver(String workflowName, String actionName, String actorName) throws ServiceUnavailableException {
		
		WorkflowService service;
		try {
			service = new WorkflowService(new URL(URL));
			WorkflowControlInterface proxy = service.getWorkflowControl();
			
			ActionInProcessType actionInProcess = new ActionInProcessType();
			actionInProcess.setActorName(actorName);
			
			ActionInProcessType.Action action = new ActionInProcessType.Action();
			action.setActionName(actionName);
			actionInProcess.setAction(action);
			
			ActionInProcessType.ProcessIdentifier pID = new ActionInProcessType.ProcessIdentifier();
			pID.setWorkflow(workflowName);
			actionInProcess.setProcessIdentifier(pID);
			
			proxy.takeOverAction(actionInProcess);			
			
			
		} catch (MalformedURLException e) {
			//e.printStackTrace();
			throw new ServiceUnavailableException();
		} catch (TakeOverActionFault e) {
			//e.printStackTrace();
			return false;
		}
		
		
		return true;
	}

}
