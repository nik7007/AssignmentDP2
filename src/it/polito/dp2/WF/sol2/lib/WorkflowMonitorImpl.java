package it.polito.dp2.WF.sol2.lib;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import it.polito.dp2.WF.ActionReader;
import it.polito.dp2.WF.ActionStatusReader;
import it.polito.dp2.WF.Actor;
import it.polito.dp2.WF.ProcessReader;
import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowMonitorException;
import it.polito.dp2.WF.WorkflowReader;
import it.polito.dp2.WF.sol2.jaxb.ActionStatusType;
import it.polito.dp2.WF.sol2.jaxb.ActionType;
import it.polito.dp2.WF.sol2.jaxb.ActorType;
import it.polito.dp2.WF.sol2.jaxb.ProcessActionType;
import it.polito.dp2.WF.sol2.jaxb.ProcessType;
import it.polito.dp2.WF.sol2.jaxb.RootType;
import it.polito.dp2.WF.sol2.jaxb.SimpleActionType;
import it.polito.dp2.WF.sol2.jaxb.SubActionType;
import it.polito.dp2.WF.sol2.jaxb.WorkflowType;
import it.polito.dp2.WF.sol2.reference.Reference;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class WorkflowMonitorImpl implements WorkflowMonitor {

	private Map<String, WorkflowReader> workflowReaderMap;
	private Map<Calendar, ProcessReader> processReaderMap;

	public WorkflowMonitorImpl() throws WorkflowReaderException,
			WorkflowMonitorException {
		workflowReaderMap = new HashMap<>();
		processReaderMap = new HashMap<>();
		String fileName = System
				.getProperty("it.polito.dp2.WF.sol2.WorkflowInfo.file");
		// System.err.println(fileName);

		RootType root = createRoot(fileName);
		assert root != null;
		List<WorkflowType> workflowTypes = root.getWorkflow();
		List<ProcessType> processTypes = root.getProcess();

		for (WorkflowType workflowType : workflowTypes) {

			Map<String, List<String>> actionConnector = new HashMap<>();
			Map<String, ActionReaderImp> allActions = new HashMap<>();

			createWorkflow(workflowType, actionConnector, allActions);

			for (Map.Entry<String, List<String>> entry : actionConnector
					.entrySet()) {
				String actionKey = entry.getKey();
				List<String> actionList = entry.getValue();

				ActionReader actionReader = allActions.get(actionKey);

				for (String action : actionList) {
					ActionReader actionR = allActions.get(action);
					if (actionR != null)
						((SimpleActionReaderImp) actionReader)
								.addActionReader(actionR);
				}
			}

		}

		for (ProcessType processType : processTypes) {

			ProcessReader processReader = createProcess(processType);
			processReaderMap.put(processReader.getStartTime(), processReader);

		}
	}

	private ProcessReader createProcess(ProcessType processType) throws WorkflowReaderException {

		GregorianCalendar date = processType.getDate().toGregorianCalendar();
		WorkflowReader workflowReader = getWorkflowReader(((WorkflowType) processType
				.getWorkflow()).getName());

		ProcessReader processReader = new ProcessReaderImp(date, workflowReader);

		List<ActionStatusType> actionStatusList = processType.getActionStatus();

		for (ActionStatusType actionStatusType : actionStatusList) {

			if (workflowReader.getAction(actionStatusType.getName()) == null) {

				throw new WorkflowReaderException(actionStatusType.getName()
						+ "does not exist!");
			}

			((ProcessReaderImp) processReader)
					.addActionStatusReader(createActionStatus(actionStatusType));
		}

		return processReader;
	}

	private ActionStatusReader createActionStatus(
			ActionStatusType actionStatusType) {

		ActionStatusReader actionStatusReader = new ActionStatusReaderImp(
				actionStatusType.getName());

		if (actionStatusType.getDate() != null)
			((ActionStatusReaderImp) actionStatusReader)
					.terminate(actionStatusType.getDate().toGregorianCalendar());

		if (actionStatusType.getActor() != null) {
			((ActionStatusReaderImp) actionStatusReader)
					.takeInCharge(createActor(actionStatusType.getActor()));
		}

		return actionStatusReader;
	}

	private Actor createActor(ActorType actorType) {
		return new Actor(actorType.getName(), actorType.getRole());

	}

	private WorkflowReader getWorkflowReader(String name) {

		if (workflowReaderMap.containsKey(name))
			return workflowReaderMap.get(name);
		WorkflowReader workflowReader = new WorkflowReaderImp(name);

		workflowReaderMap.put(name, workflowReader);
		return workflowReader;
	}

	private void createWorkflow(WorkflowType workflow, Map<String, List<String>> actionConnector, Map<String, ActionReaderImp> allActions) throws WorkflowMonitorException {

		WorkflowReader workflowReader = createWorkflow(workflow);

		List<ActionType> actionTypes = workflow
				.getSimpleActionOrProcessAction();

		for (ActionType actionType : actionTypes) {
			ActionReaderImp actionReaderImp = createAction(actionType,
					workflowReader, actionConnector);
			allActions.put(actionReaderImp.getName(), actionReaderImp);
			try {
				((WorkflowReaderImp) workflowReader)
						.addActionReader(actionReaderImp);
			} catch (WorkflowReaderException e) {
				// e.printStackTrace();
				throw new WorkflowMonitorException();
			}
		}

	}

	private WorkflowReader createWorkflow(WorkflowType workflow) {
		return getWorkflowReader(workflow.getName());

	}

	private ActionReaderImp createAction(ActionType action, WorkflowReader workflowReader, Map<String, List<String>> actionConnector) {

		ActionReaderImp actionReader;

		String name = action.getName();
		String role = action.getRole();
		Boolean auto = action.isAuto();

		if (action instanceof SimpleActionType) {

			actionReader = new SimpleActionReaderImp(name, workflowReader,
					role, auto);

			List<SubActionType> subActions = ((SimpleActionType) action)
					.getSubAction();
			if (subActions != null && !subActions.isEmpty()) {
				List<String> subName = new LinkedList<>();
				for (SubActionType subActionType : subActions) {
					subName.add(subActionType.getNameRef());
				}
				actionConnector.put(name, subName);

			}

		} else {

			WorkflowType workflowType = (WorkflowType) ((ProcessActionType) action)
					.getSubWorkflow();

			WorkflowReader wr = createWorkflow(workflowType);

			actionReader = new ProcessActionReaderImp(name, workflowReader,
					role, auto, wr);

		}

		return actionReader;

	}

	private RootType createRoot(String fileName) throws WorkflowMonitorException {

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Reference.bindingPackage);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(W3C_XML_SCHEMA_NS_URI);

			Schema schema = schemaFactory.newSchema(new File("..//xsd//WFInfo.xsd"));

			unmarshaller.setSchema(schema);

			JAXBElement<RootType> jaxbElementRoot = (JAXBElement<RootType>) unmarshaller.unmarshal(new File(fileName));

			return jaxbElementRoot.getValue();

		} catch (JAXBException | SAXException e) {
			// e.printStackTrace();
			throw new WorkflowMonitorException();
		}

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
