package it.polito.dp2.WF.sol3.lib;


import it.polito.dp2.WF.ProcessReader;
import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowMonitorException;
import it.polito.dp2.WF.WorkflowReader;
import it.polito.dp2.WF.lab3.Refreshable;
import it.polito.dp2.WF.lab3.gen.*;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class WorkflowMonitorImpl implements WorkflowMonitor, Refreshable {

    private Map<String, WorkflowReader> workflowReaderMap;

    private WorkflowInfo proxy;

    private List<String> workflowNames = null;

    private XMLGregorianCalendar lastModTimeWorkflow = null;
   
    private boolean needExcemption = false;


    public WorkflowMonitorImpl() throws MalformedURLException, WorkflowMonitorException {

        this.workflowReaderMap = new HashMap<>();
        String url = System.getProperty("it.polito.dp2.WF.sol3.URL");
        //System.err.println(url);
        WorkflowInfoService service = new WorkflowInfoService(new URL(url));
        this.proxy = service.getWorkflowInfoPort();
        this.refresh();
        if(needExcemption){
        	throw new WorkflowMonitorException();
        }
    }

    private void getWorkflowNames() {
        Holder<List<String>> holderWorkflowNames = new Holder<>();
        Holder<XMLGregorianCalendar> holderLastWorkflow = new Holder<>();

        this.proxy.getWorkflowNames(holderLastWorkflow, holderWorkflowNames);
        this.workflowNames = holderWorkflowNames.value;

    }

    private void getWorkflowForServer() throws UnknownNames_Exception, WorkflowMonitorException {

        Holder<XMLGregorianCalendar> holderLastWorkflow = new Holder<>();
        Holder<List<Workflow>> workflow = new Holder<>();

        //if (workflowNames == null)
        this.getWorkflowNames();

        this.proxy.getWorkflows(workflowNames, holderLastWorkflow, workflow);

        if (lastModTimeWorkflow == null || !lastModTimeWorkflow.equals(holderLastWorkflow.value)) {

            this.workflowReaderMap.clear();

            this.lastModTimeWorkflow = holderLastWorkflow.value;
            List<Workflow> workflowList = workflow.value;

            for (Workflow wf : workflowList) {

                Map<String, List<String>> actionConnector = new HashMap<>();
                Map<String, ActionReaderImp> allActions = new HashMap<>();

                this.createWorkflow(wf, actionConnector, allActions);
            }


        }


    }

    private void createWorkflow(Workflow workflow, Map<String, List<String>> actionConnector, Map<String, ActionReaderImp> allActions) throws WorkflowMonitorException {

        WorkflowReader workflowReader = createWorkflow(workflow);

        List<Action> actions = workflow.getAction();

        for (Action action : actions) {
            ActionReaderImp actionReaderImp = this.createAction(action, workflowReader, actionConnector);
            allActions.put(actionReaderImp.getName(), actionReaderImp);

            try {
                ((WorkflowReaderImp) workflowReader).addActionReader(actionReaderImp);
            } catch (WorkflowReaderException e) {
                //e.printStackTrace();
            	throw new WorkflowMonitorException();
            }
        }

    }

    private ActionReaderImp createAction(Action action, WorkflowReader workflowReader, Map<String, List<String>> actionConnector) {

        ActionReaderImp actionReader;

        String name = action.getName();
        String role = action.getRole();
        boolean auto = action.isAutomaticallyInstantiated();

        if (action.getWorkflow() != null)
            actionReader = new ProcessActionReaderImp(name, workflowReader, role, auto, getWorkflow(action.getWorkflow()));
        else {
            actionReader = new SimpleActionReaderImp(name, workflowReader, role, auto);
            List<String> subAction = action.getNextAction();
            actionConnector.put(name, subAction);
        }

        return actionReader;

    }

    private WorkflowReader createWorkflow(Workflow workflow) {
        return getWorkflowReader(workflow.getName());

    }

    private WorkflowReader getWorkflowReader(String name) {

        if (this.workflowReaderMap.containsKey(name))
            return this.workflowReaderMap.get(name);
        WorkflowReader workflowReader = new WorkflowReaderImp(name);

        this.workflowReaderMap.put(name, workflowReader);
        return workflowReader;
    }

    @Override
    public Set<WorkflowReader> getWorkflows() {
        return new HashSet<>(this.workflowReaderMap.values());
    }

    @Override
    public WorkflowReader getWorkflow(String s) {

        if (this.workflowReaderMap.containsKey(s))
            return this.workflowReaderMap.get(s);
        else
            return null;
    }

    @Override
    public Set<ProcessReader> getProcesses() {
        return new HashSet<>();
    }

    @Override
    public void refresh() {

        this.getWorkflowNames();
        needExcemption = false;
        try {
            this.getWorkflowForServer();
        } catch (UnknownNames_Exception | WorkflowMonitorException e) {
            e.printStackTrace();
            System.err.println("Refresh failed!");
            needExcemption = true;
        }
    }
}