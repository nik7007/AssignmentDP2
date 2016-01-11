package it.polito.dp2.WF.sol4.server;


import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowMonitorException;
import it.polito.dp2.WF.WorkflowMonitorFactory;
import it.polito.dp2.WF.sol4.server.reference.Reference;

import javax.xml.ws.Endpoint;
import java.util.concurrent.Executors;

public class WorkflowServer {

    public static void main(String[] args) {

        WorkflowMonitorFactory monitorFactory = WorkflowMonitorFactory.newInstance();
        WorkflowMonitor monitor = null;

        try {
            monitor = monitorFactory.newWorkflowMonitor();
        } catch (WorkflowMonitorException e) {
            e.printStackTrace();
        }

        Endpoint info = Endpoint.create(new WorkflowInfoService());
        info.setExecutor(Executors.newFixedThreadPool(Reference.THREAD_NUMBER));
        info.publish("http://localhost:7071/wfinfo");

        Endpoint control = Endpoint.create(new WorkflowControlService());
        control.setExecutor(Executors.newFixedThreadPool(Reference.THREAD_NUMBER));
        control.publish("http://localhost:7070/wfcontrol");

    }

}
