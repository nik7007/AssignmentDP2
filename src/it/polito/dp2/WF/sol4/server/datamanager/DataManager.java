package it.polito.dp2.WF.sol4.server.datamanager;


import it.polito.dp2.WF.lab4.gen.ProcessType;
import it.polito.dp2.WF.lab4.gen.WorkflowType;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataManager {
    private NamesHolder workflowNames;
    private Map<String, WorkflowHolder> workflowMap;
    private Map<String, Map<GregorianCalendar, ProcessHolder>> processMap;
    private boolean isEmpty = false;

    private static final DataManager DATA_MANAGER = new DataManager();

    static DataManager getInstance() {
        return DATA_MANAGER;
    }

    void setEmpty(boolean empty) {
        this.isEmpty = empty;
    }

    private DataManager() {
        this.workflowMap = new ConcurrentHashMap<>();
        this.workflowNames = new NamesHolder();
        this.processMap = new ConcurrentHashMap<>();
    }

    public void addWorkflow(WorkflowType workflow) {
        this.workflowNames.addName(workflow.getName());
        this.workflowMap.put(workflow.getName(), new WorkflowHolder(workflow));
    }

    public List<String> getWorkflowNames() {
        return this.workflowNames.getNames();
    }

    public GregorianCalendar getWorkflowNamesLastMod() {
        return this.workflowNames.getLastMod();
    }

    public boolean containWorkflow(String name) {
        try {
            return workflowMap.containsKey(name);
        } catch (ClassCastException e) {
            return false;
        }
    }

    public List<WorkflowHolder> getWorkflowByNames(List<String> names) throws GetWorkflowNameException {

        if (names == null)
            return null;

        List<WorkflowHolder> result = new LinkedList<>();

        for (String name : names) {
            if (name != null) {
                WorkflowHolder wH = this.workflowMap.get(name);

                if (wH == null || isEmpty) {
                	throw new GetWorkflowNameException(name);
                }

                result.add(wH);
            }
        }

        return result;
    }

    public WorkflowType getWorkflow(String wfName) {
        WorkflowHolder holder = this.workflowMap.get(wfName);
        return holder == null ? null : holder.getWorkflow();
    }

    public GregorianCalendar addNewProcess(ProcessType process) {

        if (process == null || isEmpty)
            return null;

        WorkflowType workflow = (WorkflowType) process.getWorkflow();

        if (workflow == null)
            return null;

        String name = workflow.getName();
        GregorianCalendar date = process.getDate().toGregorianCalendar();
        
        if(!containWorkflow(name))
        	return null;

        boolean flag = true;

        synchronized (this) {
            if (!processMap.containsKey(name)) {
                Map<GregorianCalendar, ProcessHolder> toPut = new ConcurrentHashMap<>();
                toPut.put(date, new ProcessHolder(process));
                processMap.put(name, toPut);
                flag = false;
            }

        }

        if (flag) {
            Map<GregorianCalendar, ProcessHolder> map = processMap.get(name);

            synchronized (this) {
                if (map.containsKey(date)) {
                    process.getDate().setMillisecond(process.getDate().getMillisecond() + 1);
                    date = process.getDate().toGregorianCalendar();
                }
                map.put(date, new ProcessHolder(process));
            }

        }


        return date;

    }

    public synchronized boolean isProcessPresent(ProcessType process) {

        if (process == null || isEmpty)
            return false;

        WorkflowType workflow = (WorkflowType) process.getWorkflow();

        if (workflow == null)
            return false;

        String name = workflow.getName();
        GregorianCalendar date = process.getDate().toGregorianCalendar();

        return isProcessPresent(name, date);

    }

    public synchronized boolean isProcessPresent(String wfName, GregorianCalendar date) {

        return !isEmpty && processMap.containsKey(wfName) && processMap.get(wfName).containsKey(date);
    }

    public GregorianCalendar updateProcess(ProcessType process) {

        if (isEmpty || process == null)
            return null;

        WorkflowType workflow = (WorkflowType) process.getWorkflow();

        if (workflow == null)
            return null;

        String name = workflow.getName();
        GregorianCalendar date = process.getDate().toGregorianCalendar();


        if (!isProcessPresent(process))
            return null;

        processMap.get(name).put(date, new ProcessHolder(process));

        return date;
    }

    public Map<GregorianCalendar, ProcessHolder> getProcessesByWFName(String wfName) {
        return processMap.get(wfName);
    }

    public ProcessHolder getProcess(String wfName, GregorianCalendar date) {
        return getProcessesByWFName(wfName).get(date);
    }
}
