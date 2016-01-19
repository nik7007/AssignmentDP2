package it.polito.dp2.WF.sol4.client1.workflowmonitor;


import it.polito.dp2.WF.*;
import it.polito.dp2.WF.lab4.gen.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class WorkflowMonitorImpl implements WorkflowMonitor {

    private WorkflowInfoInterface proxy;
    private List<String> workflowNames = null;

    private Map<String, WorkflowReader> workflowReaderMap;
    private Map<Calendar, ProcessReader> processReaderMap;

    public WorkflowMonitorImpl() throws MalformedURLException, GetInfoWorkflowFault_Exception, WorkflowReaderException, GetInfoProcessNameFault_Exception {

        String url = System.getProperty("it.polito.dp2.WF.lab4.URL");
        if (url == null)
            url = "http://localhost:7071/wfinfo";

        WorkflowService service = new WorkflowService(new URL(url));
        proxy = service.getWorkflowInfo();

        workflowReaderMap = new HashMap<>();
        processReaderMap = new HashMap<>();

        this.getWorkflowNames();
        this.generatedWorkflow();
        this.generateProcesses();
    }


    private void getWorkflowNames() {
        GetWorkflowNameResponseType result = proxy.getInfoWorkflowName(new GetWorkflowName());
        workflowNames = result.getWorkflowName();
    }

    private void generateProcesses() throws GetInfoProcessNameFault_Exception, WorkflowReaderException {

        for (String wfName : workflowNames) {
            GetProcessName processName = new GetProcessName();
            processName.setWorkflowName(wfName);

            GetProcessNameResponse processResponse = proxy.getInfoProcessName(processName);

            for (GetProcessNameResponse.ProcessAndTime processAndTime : processResponse.getProcessAndTime()) {

                ProcessType processType = processAndTime.getProcess();
                ProcessReader processReader = createProcess(processType);
                processReaderMap.put(processReader.getStartTime(), processReader);

            }


        }

    }

    private ActionStatusReader createActionStatus(ActionStatusType actionStatusType) {

        ActionStatusReader actionStatusReader = new ActionStatusReaderImp(actionStatusType.getName());

        if (actionStatusType.getDate() != null)
            ((ActionStatusReaderImp) actionStatusReader).terminate(actionStatusType.getDate().toGregorianCalendar());

        if (actionStatusType.getActor() != null) {
            ((ActionStatusReaderImp) actionStatusReader).takeInCharge(createActor(actionStatusType.getActor()));
        }

        return actionStatusReader;
    }

    private Actor createActor(ActorType actorType) {
        return new Actor(actorType.getName(), actorType.getRole());

    }

    private ProcessReader createProcess(ProcessType processType) throws WorkflowReaderException {

        GregorianCalendar date = processType.getDate().toGregorianCalendar();
        WorkflowReader workflowReader = getWorkflowReader(((WorkflowType) processType.getWorkflow()).getName());

        ProcessReader processReader = new ProcessReaderImp(date, workflowReader);

        List<ActionStatusType> actionStatusList = processType.getActionStatus();

        for (ActionStatusType actionStatusType : actionStatusList) {
            ((ProcessReaderImp) processReader).addActionStatusReader(createActionStatus(actionStatusType));
        }

        return processReader;
    }

    private void generatedWorkflow() throws GetInfoWorkflowFault_Exception {

        GetWorkflowByName names = new GetWorkflowByName();
        names.getWorkflowName().addAll(workflowNames);

        for (GetWorkflow.LastModTimeAndWorkflow wf : proxy.getInfoWorkflow(names).getLastModTimeAndWorkflow()) {

            Map<String, List<String>> actionConnector = new HashMap<>();
            Map<String, ActionReaderImp> allActions = new HashMap<>();

            this.createWorkflow(wf.getWorkflow(), actionConnector, allActions);

            for (Map.Entry<String, List<String>> entry : actionConnector.entrySet()) {
                String actionKey = entry.getKey();
                List<String> actionList = entry.getValue();

                ActionReader actionReader = allActions.get(actionKey);

                for (String action : actionList) {
                    ActionReader actionR = allActions.get(action);
                    if (actionR != null)
                        ((SimpleActionReaderImp) actionReader).addActionReader(actionR);
                }
            }
        }


    }

    private void createWorkflow(WorkflowType workflow, Map<String, List<String>> actionConnector, Map<String, ActionReaderImp> allActions) {

        WorkflowReader workflowReader = createWorkflow(workflow);

        List<ActionType> actionTypes = workflow.getSimpleActionOrProcessAction();

        for (ActionType actionType : actionTypes) {
            ActionReaderImp actionReaderImp = createAction(actionType, workflowReader, actionConnector);
            allActions.put(actionReaderImp.getName(), actionReaderImp);
            try {
                ((WorkflowReaderImp) workflowReader).addActionReader(actionReaderImp);
            } catch (WorkflowReaderException e) {
                e.printStackTrace();
            }
        }

    }

    private WorkflowReader createWorkflow(WorkflowType workflow) {
        return getWorkflowReader(workflow.getName());

    }

    private WorkflowReader getWorkflowReader(String name) {

        if (workflowReaderMap.containsKey(name))
            return workflowReaderMap.get(name);
        WorkflowReader workflowReader = new WorkflowReaderImp(name);

        workflowReaderMap.put(name, workflowReader);
        return workflowReader;
    }

    private ActionReaderImp createAction(ActionType action, WorkflowReader workflowReader, Map<String, List<String>> actionConnector) {

        ActionReaderImp actionReader;

        String name = action.getName();
        String role = action.getRole();
        Boolean auto = action.isAuto();

        if (action instanceof SimpleActionType) {

            actionReader = new SimpleActionReaderImp(name, workflowReader, role, auto);

            List<SubActionType> subActions = ((SimpleActionType) action).getSubAction();
            if (subActions != null && !subActions.isEmpty()) {
                List<String> subName = new LinkedList<>();
                for (SubActionType subActionType : subActions) {
                    subName.add(subActionType.getNameRef());
                }
                actionConnector.put(name, subName);

            }

        } else {

            String workflowName = ((ProcessActionType) action).getSubWorkflow();

            WorkflowReader wr = getWorkflowReader(workflowName);

            actionReader = new ProcessActionReaderImp(name, workflowReader, role, auto, wr);


        }

        return actionReader;

    }

    @Override
    public Set<WorkflowReader> getWorkflows() {
        return new HashSet<>(workflowReaderMap.values());
    }

    @Override
    public WorkflowReader getWorkflow(String s) {

        if (workflowReaderMap.containsKey(s))
            return workflowReaderMap.get(s);
        else
            return null;
    }

    @Override
    public Set<ProcessReader> getProcesses() {
        return new HashSet<>(processReaderMap.values());
    }
}