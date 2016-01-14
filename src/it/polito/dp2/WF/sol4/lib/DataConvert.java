package it.polito.dp2.WF.sol4.lib;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import it.polito.dp2.WF.*;
import it.polito.dp2.WF.lab4.gen.*;

import java.util.*;

public class DataConvert {

    private Map<String, WorkflowType> map;

    public DataConvert() {
        this(new HashMap<String, WorkflowType>());
    }

    public DataConvert(Map<String, WorkflowType> map) {
        this.map = map;
    }

    public void clear(){
        this.map.clear();
    }


    public WorkflowType getWorkflowType(String name) {

        WorkflowType workflowType;
        if (map.containsKey(name)) {
            workflowType = map.get(name);
        } else {
            workflowType = new WorkflowType();
            map.put(name, workflowType);
        }

        return workflowType;

    }

    public WorkflowType createWokWorkflow(WorkflowReader workflow, WorkflowType workflowType) {

        workflowType.setName(workflow.getName());

        List<ActionType> actionsType = workflowType.getSimpleActionOrProcessAction();
        Set<ActionReader> actions = workflow.getActions();

        for (ActionReader actionReader : actions)
            actionsType.add(createAction(actionReader));

        return workflowType;
    }

    public WorkflowType createWokWorkflow(WorkflowReader workflow) {
        WorkflowType workflowType = getWorkflowType(workflow.getName());

        return createWokWorkflow(workflow, workflowType);
    }

    public ActionType createAction(ActionReader action) {
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

    public SubActionType createSubAction(ActionReader action) {

        SubActionType subAction = new SubActionType();

        subAction.setNameRef(action.getName());

        return subAction;

    }

    public ProcessType createProcess(ProcessReader process) {

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

    public ActionStatusType createActionStatus(ActionStatusReader actionStatus) {

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

    public ActorType createActor(Actor actor) {
        ActorType actorType = new ActorType();

        actorType.setName(actor.getName());
        actorType.setRole(actor.getRole());

        return actorType;
    }
}
