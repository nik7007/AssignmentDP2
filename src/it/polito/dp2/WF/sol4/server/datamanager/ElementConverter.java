package it.polito.dp2.WF.sol4.server.datamanager;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import it.polito.dp2.WF.*;
import it.polito.dp2.WF.lab4.gen.*;

import java.util.*;

public class ElementConverter {

    private static final ElementConverter ELEMENT_CONVERTER = new ElementConverter();

    private WorkflowMonitor monitor;
    Map<String, WorkflowType> map;


    private ElementConverter() {
        try {
            WorkflowMonitorFactory monitorFactory = WorkflowMonitorFactory.newInstance();
            monitor = monitorFactory.newWorkflowMonitor();
        } catch (WorkflowMonitorException e) {
            //e.printStackTrace();
            monitor = null;
        }
        map = new HashMap<>();
    }

    public static DataManager init() {

        DataManager dataManager = DataManager.getInstance();

        WorkflowMonitor monitor = ELEMENT_CONVERTER.monitor;
        ElementConverter e = ELEMENT_CONVERTER;

        if (monitor == null)
            dataManager.setEmpty(true);
        else {
            Set<WorkflowReader> workflowSet = monitor.getWorkflows();
            Set<ProcessReader> processSet = monitor.getProcesses();

            for (WorkflowReader workflowReader : workflowSet)
                dataManager.addWorkflow(e.createWokWorkflow(workflowReader));
            for (ProcessReader processReader : processSet)
                dataManager.addNewProcess(e.createProcess(processReader));

            e.map.clear();

        }

        return dataManager;

    }

    private WorkflowType getWorkflowType(String name) {

        WorkflowType workflowType;
        if (map.containsKey(name)) {
            workflowType = map.get(name);
        } else {
            workflowType = new WorkflowType();
            map.put(name, workflowType);
        }

        return workflowType;

    }

    private WorkflowType createWokWorkflow(WorkflowReader workflow, WorkflowType workflowType) {

        workflowType.setName(workflow.getName());

        List<ActionType> actionsType = workflowType.getSimpleActionOrProcessAction();
        Set<ActionReader> actions = workflow.getActions();

        for (ActionReader actionReader : actions)
            actionsType.add(createAction(actionReader));

        return workflowType;
    }

    private WorkflowType createWokWorkflow(WorkflowReader workflow) {
        WorkflowType workflowType = getWorkflowType(workflow.getName());

        return createWokWorkflow(workflow, workflowType);
    }

    private ActionType createAction(ActionReader action) {
        ActionType actionType = null;


        if (action instanceof SimpleActionReader) {
            actionType = new SimpleActionType();

            Set<ActionReader> subActions = ((SimpleActionReader) action).getPossibleNextActions();
            List<SubActionType> subActionsType = ((SimpleActionType) actionType).getSubAction();

            for (ActionReader actionReader : subActions)
                subActionsType.add(createSubAction(actionReader));


        } else if (action instanceof ProcessActionReader) {

            actionType = new ProcessActionType();

            String workflowName = ((ProcessActionReader) action).getActionWorkflow().getName();

            WorkflowType workflowType = getWorkflowType(workflowName);


            ((ProcessActionType) actionType).setSubWorkflow(workflowType);
        }

        assert actionType != null;

        actionType.setName(action.getName());
        actionType.setAuto(action.isAutomaticallyInstantiated());
        actionType.setRole(action.getRole());


        return actionType;

    }

    private SubActionType createSubAction(ActionReader action) {

        SubActionType subAction = new SubActionType();

        subAction.setNameRef(action.getName());

        return subAction;

    }

    private ProcessType createProcess(ProcessReader process) {

        ProcessType processType = new ProcessType();
        WorkflowType workflowType = getWorkflowType(process.getWorkflow().getName());
        processType.setWorkflow(workflowType);

        processType.setDate(new XMLGregorianCalendarImpl((GregorianCalendar) process.getStartTime()));

        List<ActionStatusReader> actionStatus = process.getStatus();
        List<ActionStatusType> actionStatusTypes = processType.getActionStatus();

        for (ActionStatusReader actionStatusReader : actionStatus)
            actionStatusTypes.add(createActionStatus(actionStatusReader));


        return processType;
    }

    private ActionStatusType createActionStatus(ActionStatusReader actionStatus) {

        ActionStatusType actionStatusType = new ActionStatusType();
        Actor actor = actionStatus.getActor();

        actionStatusType.setName(actionStatus.getActionName());
        actionStatusType.setTakenInCharge(actionStatus.isTakenInCharge());
        actionStatusType.setTerminated(actionStatus.isTerminated());

        if (actionStatus.getTerminationTime() != null)
            actionStatusType.setDate(new XMLGregorianCalendarImpl((GregorianCalendar) actionStatus.getTerminationTime()));

        if (actor != null)
            actionStatusType.setActor(createActor(actor));

        return actionStatusType;

    }

    private ActorType createActor(Actor actor) {
        ActorType actorType = new ActorType();

        actorType.setName(actor.getName());
        actorType.setRole(actor.getRole());

        return actorType;
    }
}
