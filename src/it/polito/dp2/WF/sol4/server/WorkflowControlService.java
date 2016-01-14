package it.polito.dp2.WF.sol4.server;


import it.polito.dp2.WF.lab4.gen.*;
import it.polito.dp2.WF.sol4.server.datamanager.DataManager;
import it.polito.dp2.WF.sol4.server.reference.Reference;

import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;


@WebService(
        name = "WorkflowControllnterface",
        targetNamespace = Reference.TARGET_NAMESPACE,
        serviceName = Reference.SERVICE_NAME,
        portName = "WorkflowControl",
        endpointInterface = Reference.BASIC_PACKAGE + "WorkflowControllnterface"
)
public class WorkflowControlService implements WorkflowControllnterface {

    final DataManager DM;

    public WorkflowControlService() {
        this.DM = WorkflowServer.DATA_MANAGER;
    }

    @Override
    public XMLGregorianCalendar creatProcess(String parameters) throws CreatProcessFault {
        return null;
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
