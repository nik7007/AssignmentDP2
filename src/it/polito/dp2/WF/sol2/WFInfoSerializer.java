package it.polito.dp2.WF.sol2;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import it.polito.dp2.WF.*;
import it.polito.dp2.WF.sol2.jaxb.*;
import it.polito.dp2.WF.sol2.reference.Reference;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

public class WFInfoSerializer {

    JAXBContext jaxbContext;
    RootType root;

    public WFInfoSerializer() throws WorkflowMonitorException, JAXBException {

        WorkflowMonitorFactory factory = WorkflowMonitorFactory.newInstance();
        WorkflowMonitor monitor = factory.newWorkflowMonitor();

        jaxbContext = JAXBContext.newInstance(Reference.bindingPackage);
        root = createRoot(monitor);
    }

    RootType createRoot(WorkflowMonitor monitor) {

        RootType root = new RootType();

        Set<WorkflowReader> workflowSet = monitor.getWorkflows();
        Set<ProcessReader> processSet = monitor.getProcesses();

        List<WorkflowType> workflowList = root.getWorkflow();
        List<ProcessType> processList = root.getProcess();

        for (WorkflowReader workflowReader : workflowSet)
            workflowList.add(createWokWorkflow(workflowReader));

        for (ProcessReader processReader : processSet)
            processList.add(createProcess(processReader));

        return root;
    }

    ProcessType createProcess(ProcessReader process) {

        ProcessType processType = new ProcessType();

        processType.setWorkflow(process.getWorkflow().getName());

        processType.setDate(new XMLGregorianCalendarImpl((GregorianCalendar) process.getStartTime()));

        List<ActionStatusReader> actionStatus = process.getStatus();
        List<ActionStatusType> actionStatusTypes = processType.getActionStatus();

        for (ActionStatusReader actionStatusReader : actionStatus)
            actionStatusTypes.add(createActionStatus(actionStatusReader));


        return processType;
    }

    ActionStatusType createActionStatus(ActionStatusReader actionStatus) {

        ActionStatusType actionStatusType = new ActionStatusType();
        Actor actor = actionStatus.getActor();

        actionStatusType.setName(actionStatus.getActionName());
        actionStatusType.setTakenInCharge(actionStatus.isTakenInCharge());
        actionStatusType.setTerminated(actionStatus.isTerminated());
        actionStatusType.setDate(new XMLGregorianCalendarImpl((GregorianCalendar) actionStatus.getTerminationTime()));

        if (actor != null)
            actionStatusType.setActor(createActor(actor));

        return actionStatusType;

    }

    ActorType createActor(Actor actor) {
        ActorType actorType = new ActorType();

        actorType.setName(actor.getName());
        actorType.setRole(actor.getRole());

        return actorType;
    }


    WorkflowType createWokWorkflow(WorkflowReader workflow) {
        WorkflowType workflowType = new WorkflowType();
        workflowType.setName(workflow.getName());

        List<ActionType> actionsType = workflowType.getSimpleActionOrProcessAction();
        Set<ActionReader> actions = workflow.getActions();

        for (ActionReader actionReader : actions)
            actionsType.add(createAction(actionReader));

        return workflowType;
    }

    ActionType createAction(ActionReader action) {
        ActionType actionType = null;


        if (action instanceof SimpleActionReader) {
            actionType = new SimpleActionType();

            Set<ActionReader> subActions = ((SimpleActionReader) action).getPossibleNextActions();
            List<SubActionType> subActionsType = ((SimpleActionType) actionType).getSubAction();

            for (ActionReader actionReader : subActions)
                subActionsType.add(createSubAction(actionReader));


        } else if (action instanceof ProcessActionReader) {

            actionType = new ProcessActionType();
            ((ProcessActionType) actionType).setSubWorkflow(((ProcessActionReader) action).getActionWorkflow().getName());
        }

        assert actionType != null;

        actionType.setName(action.getName());
        actionType.setAuto(action.isAutomaticallyInstantiated());
        actionType.setRole(action.getRole());


        return actionType;

    }

    SubActionType createSubAction(ActionReader action) {

        SubActionType subAction = new SubActionType();

        subAction.setNameRef(action.getName());

        return subAction;

    }

    public void marshalling(String fileName) throws JAXBException {
        JAXBElement<RootType> jaxbElement = (new ObjectFactory()).createRoot(root);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(jaxbElement, new File(fileName));

    }


    public static void main(String[] args) throws JAXBException, WorkflowMonitorException {

        WFInfoSerializer wfInfoSerializer = new WFInfoSerializer();
        String fileName = args.length <= 0 ? "output.xml" : args[0];
        wfInfoSerializer.marshalling(fileName);


    }
}
