package it.polito.dp2.WF.sol2;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import it.polito.dp2.WF.ActionReader;
import it.polito.dp2.WF.ActionStatusReader;
import it.polito.dp2.WF.Actor;
import it.polito.dp2.WF.ProcessActionReader;
import it.polito.dp2.WF.ProcessReader;
import it.polito.dp2.WF.SimpleActionReader;
import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowMonitorException;
import it.polito.dp2.WF.WorkflowReader;
import it.polito.dp2.WF.sol2.jaxb.ActionStatusType;
import it.polito.dp2.WF.sol2.jaxb.ActionType;
import it.polito.dp2.WF.sol2.jaxb.ActorType;
import it.polito.dp2.WF.sol2.jaxb.ObjectFactory;
import it.polito.dp2.WF.sol2.jaxb.ProcessActionType;
import it.polito.dp2.WF.sol2.jaxb.ProcessType;
import it.polito.dp2.WF.sol2.jaxb.RootType;
import it.polito.dp2.WF.sol2.jaxb.SimpleActionType;
import it.polito.dp2.WF.sol2.jaxb.SubActionType;
import it.polito.dp2.WF.sol2.jaxb.WorkflowType;
import it.polito.dp2.WF.sol2.lib.SerializerException;
import it.polito.dp2.WF.sol2.reference.Reference;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

public class WFInfoSerializer {

    JAXBContext jaxbContext;
    RootType root;
    Map<String, WorkflowType> map;

    public WFInfoSerializer() throws WorkflowMonitorException, JAXBException, SerializerException {

        it.polito.dp2.WF.WorkflowMonitorFactory factory = WorkflowMonitorFactory.newInstance();
        WorkflowMonitor monitor = factory.newWorkflowMonitor();
        map = new HashMap<>();

        jaxbContext = JAXBContext.newInstance(Reference.bindingPackage);
        root = createRoot(monitor);
    }

    RootType createRoot(WorkflowMonitor monitor) throws SerializerException {

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

    ProcessType createProcess(ProcessReader process) throws SerializerException {

        ProcessType processType = new ProcessType();
        WorkflowType workflowType = getWorkflowType(process.getWorkflow().getName());
        processType.setWorkflow(workflowType);

        processType.setDate(new XMLGregorianCalendarImpl((GregorianCalendar) process.getStartTime()));

        List<ActionStatusReader> actionStatus = process.getStatus();
        List<ActionStatusType> actionStatusTypes = processType.getActionStatus();

        for (ActionStatusReader actionStatusReader : actionStatus) {

            boolean flag = false;
            for(ActionType actionType :workflowType.getSimpleActionOrProcessAction()){
                if(actionType.getName().equals(actionStatusReader.getActionName()))
                {
                    flag = true;
                    break;
                }
            }

            if(!flag)
                throw new SerializerException(actionStatusReader.getActionName()+ "does not exist!");

            actionStatusTypes.add(createActionStatus(actionStatusReader));
        }


        return processType;
    }

    ActionStatusType createActionStatus(ActionStatusReader actionStatus) {

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

    ActorType createActor(Actor actor) {
        ActorType actorType = new ActorType();

        actorType.setName(actor.getName());
        actorType.setRole(actor.getRole());

        return actorType;
    }


    WorkflowType createWokWorkflow(WorkflowReader workflow, WorkflowType workflowType) {

        workflowType.setName(workflow.getName());

        List<ActionType> actionsType = workflowType.getSimpleActionOrProcessAction();
        Set<ActionReader> actions = workflow.getActions();

        for (ActionReader actionReader : actions)
            actionsType.add(createAction(actionReader));

        return workflowType;
    }

    WorkflowType createWokWorkflow(WorkflowReader workflow) {
        WorkflowType workflowType = getWorkflowType(workflow.getName());

        return createWokWorkflow(workflow, workflowType);
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

    SubActionType createSubAction(ActionReader action) {

        SubActionType subAction = new SubActionType();

        subAction.setNameRef(action.getName());

        return subAction;

    }

    public void marshalling(String fileName) throws JAXBException, SAXException {
        JAXBElement<RootType> jaxbElement = (new ObjectFactory()).createRoot(root);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        Schema schema = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI).newSchema(new File("xsd/Wfinfo.xsd"));
        marshaller.setSchema(schema);
        marshaller.marshal(jaxbElement, new File(fileName));

    }

    WorkflowType getWorkflowType(String name) {

        WorkflowType workflowType;
        if (map.containsKey(name)) {
            workflowType = map.get(name);
        } else {
            workflowType = new WorkflowType();
            map.put(name, workflowType);
        }

        return workflowType;

    }

    public static void main(String[] args) throws JAXBException, WorkflowMonitorException, SerializerException, SAXException {

        WFInfoSerializer wfInfoSerializer = new WFInfoSerializer();
        String fileName = args.length <= 0 ? "output.xml" : args[0];

        char[] ext = {'.', 'x', 'm', 'l'};
        boolean extension = true;

        for (int i = 0; i < ext.length; i++) {
            char c = fileName.charAt(fileName.length() - 4 + i);

            if (c != ext[i]) {
                extension = false;
                break;
            }
        }

        if (!extension)
            fileName += ".xml";

        wfInfoSerializer.marshalling(fileName);


    }
}
