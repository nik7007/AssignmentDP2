package it.polito.dp2.WF.sol4.server;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import it.polito.dp2.WF.lab4.gen.*;
import it.polito.dp2.WF.sol4.server.datamanager.DataManager;
import it.polito.dp2.WF.sol4.server.reference.Reference;

import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;


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
        return false;
    }

    @Override
    public boolean completeAction(ActionToCompleteType completeActionRequest) throws CompleteActionFault_Exception {
        return false;
    }
}
