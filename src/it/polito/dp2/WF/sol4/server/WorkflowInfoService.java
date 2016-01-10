package it.polito.dp2.WF.sol4.server;


import it.polito.dp2.WF.lab4.gen.*;
import it.polito.dp2.WF.sol4.server.reference.Reference;

import javax.jws.WebService;

@WebService(
        name = "WorkflowInfoInterface",
        targetNamespace = Reference.TARGET_NAMESPACE,
        serviceName = Reference.SERVICE_NAME,
        portName = "WorkflowInfo",
        wsdlLocation = Reference.WSDL_LOCATION_INFO,
        endpointInterface = Reference.BASIC_PACKAGE + "WorkflowInfoInterface"
)
public class WorkflowInfoService implements WorkflowInfoInterface {

    @Override
    public String getInfoWorkflowName(GetWorkflowName parameters) {
        return null;
    }

    @Override
    public GetProcessNameResponse getInfoProcessName(GetProcessName parameters) throws GetInfoProcessNameFault_Exception {
        return null;
    }

    @Override
    public GetWorkflow getInfoWorkflow(GetWorkflowByName parameters) throws GetInfoWorkflowFault_Exception {
        return null;
    }
}
