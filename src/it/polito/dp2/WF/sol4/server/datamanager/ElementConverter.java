package it.polito.dp2.WF.sol4.server.datamanager;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import it.polito.dp2.WF.*;
import it.polito.dp2.WF.lab4.gen.*;
import it.polito.dp2.WF.sol4.lib.DataConvert;

import java.util.*;

public class ElementConverter {

    private static final ElementConverter ELEMENT_CONVERTER = new ElementConverter();

    private WorkflowMonitor monitor;


    private ElementConverter() {
        try {
            WorkflowMonitorFactory monitorFactory = WorkflowMonitorFactory.newInstance();
            monitor = monitorFactory.newWorkflowMonitor();
        } catch (WorkflowMonitorException e) {
            //e.printStackTrace();
            monitor = null;
        }
    }

    public static DataManager init() {

        DataManager dataManager = DataManager.getInstance();

        WorkflowMonitor monitor = ELEMENT_CONVERTER.monitor;
        DataConvert convert = new DataConvert();

        if (monitor == null)
            dataManager.setEmpty(true);
        else {
            Set<WorkflowReader> workflowSet = monitor.getWorkflows();
            Set<ProcessReader> processSet = monitor.getProcesses();

            for (WorkflowReader workflowReader : workflowSet)
                dataManager.addWorkflow(convert.createWokWorkflow(workflowReader));
            for (ProcessReader processReader : processSet)
                dataManager.addNewProcess(convert.createProcess(processReader));

            convert.clear();

        }

        return dataManager;

    }

}
