package it.polito.dp2.WF.sol4.server;


import it.polito.dp2.WF.lab4.gen.ActionInProcessType;
import it.polito.dp2.WF.lab4.gen.ActionInProcessType.ProcessIdentifier;
import it.polito.dp2.WF.lab4.gen.ActionStatusType;
import it.polito.dp2.WF.lab4.gen.ActionToCompleteType;
import it.polito.dp2.WF.lab4.gen.ActionType;
import it.polito.dp2.WF.lab4.gen.ActorType;
import it.polito.dp2.WF.lab4.gen.CompleteActionFault_Exception;
import it.polito.dp2.WF.lab4.gen.CreateProcessFault;
import it.polito.dp2.WF.lab4.gen.ProcessType;
import it.polito.dp2.WF.lab4.gen.TakeOverActionFault;
import it.polito.dp2.WF.lab4.gen.WorkflowControlInterface;
import it.polito.dp2.WF.lab4.gen.WorkflowType;
import it.polito.dp2.WF.sol4.server.datamanager.DataManager;
import it.polito.dp2.WF.sol4.server.datamanager.ProcessHolder;
import it.polito.dp2.WF.sol4.server.reference.Reference;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;


@WebService(
        name = "WorkflowControllnterface",
        targetNamespace = Reference.TARGET_NAMESPACE,
        serviceName = Reference.SERVICE_NAME,
        portName = "WorkflowControl",
        endpointInterface = Reference.BASIC_PACKAGE + "WorkflowControlInterface"
)
public class WorkflowControlService implements WorkflowControlInterface {

	private final DataManager DM;

    public WorkflowControlService() {
        this.DM = WorkflowServer.DATA_MANAGER;
    }

    @Override
    public XMLGregorianCalendar createProcess(String parameters) throws CreateProcessFault {

        if (!DM.containWorkflow(parameters)) {
            String errMsg = parameters + " does not exist!";
            throw new CreateProcessFault(errMsg, errMsg);
        }

        WorkflowType workflow = DM.getWorkflow(parameters);

        GregorianCalendar calendar = DM.addNewProcess(createProcess(workflow));

        if (calendar == null) {
            String errMsg = "ERROR creating the process!!";
            throw new CreateProcessFault(errMsg, errMsg);
        }

        return new XMLGregorianCalendarImpl(calendar);
    }

    private ProcessType createProcess(WorkflowType workflowType) {

        if (workflowType == null)
            return null;

        ProcessType process = new ProcessType();

        process.setDate(new XMLGregorianCalendarImpl(new GregorianCalendar()));
        process.setWorkflow(workflowType);

        ActionStatusType action = createElement(getAutoAction(workflowType));

        process.getActionStatus().add(action);

        return process;
    }

    private ActionType getAutoAction(WorkflowType workflowType) {

        for (ActionType action : workflowType.getSimpleActionOrProcessAction()) {
            if (action.isAuto())
                return action;
        }
        return null;
    }

    private ActionStatusType createElement(ActionType action) {
        ActionStatusType actionStatus = new ActionStatusType();
        actionStatus.setName(action.getName());
        return actionStatus;
    }


    @Override
    public boolean takeOverAction(ActionInProcessType takeOverActionRequest) throws TakeOverActionFault {
    	
    	ProcessIdentifier processID = takeOverActionRequest.getProcessIdentifier();
    	String wfName = processID.getWorkflow();
    	GregorianCalendar calendar;
    	List<ProcessHolder> pHs = new LinkedList<>();
    	String actionName = takeOverActionRequest.getAction().getActionName();
    	ActorType actor = takeOverActionRequest.getActor();
    	WorkflowType workflowType = DM.getWorkflow(wfName);
    	String role = null;
    	
    	if(workflowType == null) {
    		String msg ="Workflow " + wfName + " does not exist!";
			throw new TakeOverActionFault(msg,msg);
    	}
    	
    	for(ActionType actionType : workflowType.getSimpleActionOrProcessAction()){
    		if(actionType.getName().equals(actionName)){
    			role = actionType.getRole();
    			break;
    		}
    	}
    	
    	if(role == null){
    		String msg ="Action " + actionName + " does not exist in workflow "+ wfName;
			throw new TakeOverActionFault(msg,msg);
    	}
    	
    	if(actor == null){
    		actor = new ActorType();
    		actor.setName(takeOverActionRequest.getActorName());
    		actor.setRole(role);
    	}

    	
    	boolean result = false;
    	    	
    	try{ 
    		calendar = processID.getDate().toGregorianCalendar();    	
    	}
    	catch (Exception e){
    		calendar= null;
    	}
    	
    	if(calendar != null){
    		ProcessHolder p = DM.getProcess(wfName, calendar);
    		if(p == null)
    		{
    			String msg ="Process of " + wfName + " create at " + calendar +" is not present";
    			throw new TakeOverActionFault(msg,msg);
    		}
    	pHs.add(p);    	
    	}
    	
    	 Collection<ProcessHolder> ps = DM.getWorflowProcesses(wfName);
    	 if(ps == null){
    		 
    		String msg = wfName + " has not active process!";
 			throw new TakeOverActionFault(msg,msg);
    		 
    	 }
    	pHs.addAll(ps);
    	
    	for(ProcessHolder ph : pHs){
    		
    		synchronized (ph) {
    			
			for(ActionStatusType actionStaus : ph.getProcessNoTSafe().getActionStatus()){
				
				if(actionStaus.getName().equals(actionName)){
					
						if(!actionStaus.isTakenInCharge() || actionStaus.getActor() == null)
						{
							actionStaus.setTakenInCharge(true);
							actionStaus.setActor(actor);
							result = true;
							break;
						}
							
					}
				
				}
			}
    		
    		if(result)
    			break;
    		
    	}
    	
    	if(!result){
    		String err = "There is not action " + actionName + " that can be taken over";
    		throw new TakeOverActionFault(err,err);
    	}
    	
    	
    	
        return result;
    }

    @Override
    public boolean completeAction(ActionToCompleteType completeActionRequest) throws CompleteActionFault_Exception {
        return false;
    }
}
