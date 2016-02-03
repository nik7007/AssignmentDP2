package it.polito.dp2.WF.sol4.server;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import it.polito.dp2.WF.lab4.gen.*;
import it.polito.dp2.WF.sol4.server.datamanager.DataManager;
import it.polito.dp2.WF.sol4.server.datamanager.GetWorkflowNameException;
import it.polito.dp2.WF.sol4.server.datamanager.ProcessHolder;
import it.polito.dp2.WF.sol4.server.datamanager.WorkflowHolder;
import it.polito.dp2.WF.sol4.server.reference.Reference;

import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

@WebService(
        name = "WorkflowInfoInterface",
        targetNamespace = Reference.TARGET_NAMESPACE,
        serviceName = Reference.SERVICE_NAME,
        portName = "WorkflowInfo",
        endpointInterface = Reference.BASIC_PACKAGE + "WorkflowInfoInterface"
)
public class WorkflowInfoService implements WorkflowInfoInterface {

    private final DataManager DM;

    public WorkflowInfoService() {
        this.DM = WorkflowServer.DATA_MANAGER;
    }

    @Override
    public GetWorkflowNameResponseType getInfoWorkflowName(GetWorkflowName parameters) {
        GetWorkflowNameResponseType response = new GetWorkflowNameResponseType();
        response.setLastModTime(new XMLGregorianCalendarImpl(DM.getWorkflowNamesLastMod()));
        for (String s : DM.getWorkflowNames())
            response.getWorkflowName().add(s);
        return response;
    }

    @Override
    public GetProcessNameResponse getInfoProcessName(GetProcessName parameters) throws GetInfoProcessNameFault_Exception {

        String wfName = parameters.getWorkflowName();
        Map<GregorianCalendar, ProcessHolder> map = DM.getProcessesByWFName(wfName);
        boolean isFirst = true;

        if (!DM.containWorkflow(wfName)) {
            String errS = wfName + " does not exist!";
            GetInfoProcessNameFault e = new GetInfoProcessNameFault();
            e.setGetInfoProcessNameFault(errS);
            throw new GetInfoProcessNameFault_Exception(errS, e);
        }

        GetProcessNameResponse response = new GetProcessNameResponse();

        response.getProcessAndTime();
        if (map != null)
            for (Map.Entry<GregorianCalendar, ProcessHolder> entry : map.entrySet()) {
                ProcessHolder pH = entry.getValue();

                if (isFirst) {
                    isFirst = false;
                    response.setWorkflow((WorkflowType) pH.getProcess().getWorkflow());
                }

                response.getProcessAndTime().add(createElement(new XMLGregorianCalendarImpl(pH.getLastMod()), pH.getProcessNoTSafe()));

            }

        return response;
    }

    GetProcessNameResponse.ProcessAndTime createElement(XMLGregorianCalendar date, ProcessType processType) {

        GetProcessNameResponse.ProcessAndTime result = new GetProcessNameResponse.ProcessAndTime();

        result.setLastModTime(date);
        result.setProcess(processType);

        return result;

    }


    @Override
    public GetWorkflow getInfoWorkflow(GetWorkflowByName parameters) throws GetInfoWorkflowFault_Exception {

        List<WorkflowHolder> wfList;
		try {			
			wfList = DM.getWorkflowByNames(parameters.getWorkflowName());			
		} catch (GetWorkflowNameException exc) {
			//exc.printStackTrace();
			String errMessage = exc.getMessage();
            GetInfoWorkflowFault e = new GetInfoWorkflowFault();
            e.setGetInfoWorkflowFault(errMessage);
            throw new GetInfoWorkflowFault_Exception(errMessage, e);
		}

        if (wfList == null) {
        	
            String errMessage = "ERROR";
            GetInfoWorkflowFault e = new GetInfoWorkflowFault();
            e.setGetInfoWorkflowFault(errMessage);
            throw new GetInfoWorkflowFault_Exception(errMessage, e);

        }
        
        GetWorkflow getWorkflow = new GetWorkflow();
        for (WorkflowHolder holder : wfList)
            getWorkflow.getLastModTimeAndWorkflow().add(createElement(new XMLGregorianCalendarImpl(holder.getLastMod()), holder.getWorkflow()));

        return getWorkflow;
    }

    GetWorkflow.LastModTimeAndWorkflow createElement(XMLGregorianCalendar lastModTime, WorkflowType workflow) {
        GetWorkflow.LastModTimeAndWorkflow result = new GetWorkflow.LastModTimeAndWorkflow();

        result.setLastModTime(lastModTime);
        result.setWorkflow(workflow);
        return result;
    }

}
