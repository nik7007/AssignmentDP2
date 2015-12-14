package it.polito.dp2.WF.sol3.lib;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import it.polito.dp2.WF.ProcessReader;
import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowReader;
import it.polito.dp2.WF.lab3.Refreshable;
import it.polito.dp2.WF.lab3.gen.*;
import it.polito.dp2.WF.sol2.lib.*;
import it.polito.dp2.WF.sol2.lib.WorkflowReaderException;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class WorkflowMonitorImpl implements WorkflowMonitor, Refreshable {

    private Map<String, WorkflowReader> workflowReaderMap;

    WorkflowInfo proxy;

    List<String> workflowNames = null;

    XMLGregorianCalendar lastModTimeWorkflow = null;


    public WorkflowMonitorImpl() throws MalformedURLException {
        workflowReaderMap = new HashMap<>();
        String url = System.getProperty("it.polito.dp2.WF.sol3.URL");
        //System.err.println(url);
        WorkflowInfoService service = new WorkflowInfoService(new URL(url));
        proxy = service.getWorkflowInfoPort();
    }

    void getWorkflowNames(){
        Holder<List<String>> holderWorkflowNames = new Holder<>();
        Holder<XMLGregorianCalendar> holderLastWorkflow = new Holder<>();

        proxy.getWorkflowNames(holderLastWorkflow,holderWorkflowNames);
        workflowNames = holderWorkflowNames.value;

    }

    void getWorkflowForServer() throws UnknownNames_Exception {

        Holder<XMLGregorianCalendar> holderLastWorkflow = new Holder<>();
        Holder<List<Workflow>> workflow = new Holder<>();

        if(workflowNames == null)
            getWorkflowNames();

        proxy.getWorkflows(workflowNames,holderLastWorkflow,workflow);

        if(lastModTimeWorkflow == null || !lastModTimeWorkflow.equals(holderLastWorkflow.value)){

            lastModTimeWorkflow = holderLastWorkflow.value;
            List<Workflow> workflowList = workflow.value;

            for(Workflow wf:workflowList){

                Map<String, List<String>> actionConnector = new HashMap<>();
                Map<String, it.polito.dp2.WF.sol2.lib.ActionReaderImp> allActions = new HashMap<>();

            }


        }


    }

  /*  void createWorkflow(Workflow workflow, Map<String, List<String>> actionConnector, Map<String, ActionReaderImp> allActions) {

        WorkflowReader workflowReader = createWorkflow(workflow);

        List<Action> actionTypes = workflow.getAction();

        for (Action actionType : actionTypes) {
            ActionReaderImp actionReaderImp = createAction(actionType, workflowReader, actionConnector);
            allActions.put(actionReaderImp.getName(), actionReaderImp);

            try {
                ((it.polito.dp2.WF.sol2.lib.WorkflowReaderImp)workflowReader).addActionReader(actionReaderImp);
            } catch (WorkflowReaderException e) {
                e.printStackTrace();
            }
        }

    }*/



    WorkflowReader getWorkflowReader(String name) {

        if (workflowReaderMap.containsKey(name))
            return workflowReaderMap.get(name);
        WorkflowReader workflowReader = new WorkflowReaderImp(name);

        workflowReaderMap.put(name, workflowReader);
        return workflowReader;
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
        return new HashSet<>();
    }

    @Override
    public void refresh() {

    }
}
