package it.polito.dp2.WF.sol4.server.datamanager;


import it.polito.dp2.WF.*;
import it.polito.dp2.WF.sol4.server.lib.DataConvert;

import java.util.Set;

public class ElementConverter {

    private static final ElementConverter ELEMENT_CONVERTER = new ElementConverter();

    private WorkflowMonitor monitor;


    private ElementConverter() {
        try {
            WorkflowMonitorFactory monitorFactory = WorkflowMonitorFactory.newInstance();
            monitor = monitorFactory.newWorkflowMonitor();
        } catch (FactoryConfigurationError | WorkflowMonitorException e) {
            //e.printStackTrace();
            monitor = null;
        }
    }

    public static DataManager init() {

        DataManager dataManager = DataManager.getInstance();

        WorkflowMonitor monitor = ELEMENT_CONVERTER.monitor;


        if (monitor == null)
            dataManager.setEmpty(true);
        else {

            DataConvert convert = new DataConvert();

            Set<WorkflowReader> workflowSet = monitor.getWorkflows();
            Set<ProcessReader> processSet = monitor.getProcesses();

            try{
            	
            	for (WorkflowReader workflowReader : workflowSet)
            		dataManager.addWorkflow(convert.createWokWorkflow(workflowReader));
            	for (ProcessReader processReader : processSet)
            		dataManager.addNewProcess(convert.createProcess(processReader));            

            	convert.clear();
            	
            } catch(Exception e){
            	dataManager.setEmpty(true);
            }

        }

        return dataManager;

    }

}
