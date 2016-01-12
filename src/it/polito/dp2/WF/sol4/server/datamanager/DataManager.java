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

    private static final DataManager DATA_MANAGER = new DataManager();

    public static DataManager getInstance() {
        return DATA_MANAGER;
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

    public List<WorkflowHolder> getWorkflowByNames(List<String> names) {

        List<WorkflowHolder> result = new LinkedList<>();

        for (String name : names) {
            result.add(this.workflowMap.get(name));
        }

        return result;
    }
    
    public GregorianCalendar addProcess(ProcessType process) {
    	
    	WorkflowType workflow = (WorkflowType) process.getWorkflow();
    	String name = workflow.getName();
    	GregorianCalendar date = process.getDate().toGregorianCalendar();
    	
    	boolean flag = true;
    	
    	synchronized (this) {
    		if(!processMap.containsKey(name)){
    			Map<GregorianCalendar, ProcessHolder> toPut = new ConcurrentHashMap<>();
    			toPut.put(date, new ProcessHolder(process));
    			processMap.put(name,toPut);	
    			flag =false;
    		}

		}
    	
    	if(flag){
    		Map<GregorianCalendar, ProcessHolder> map = processMap.get(name);
    		
    		synchronized (this) {
    			if(map.containsKey(date)){
    				process.getDate().setMillisecond(process.getDate().getMillisecond() +1 );
    				date = process.getDate().toGregorianCalendar();
    			}    			
    			map.put(date, new ProcessHolder(process));
    		}
    		
    	}
    	
    	
    	return date;
		
	}
}
