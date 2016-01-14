package it.polito.dp2.WF.sol4.server;


import it.polito.dp2.WF.sol4.server.datamanager.DataManager;
import it.polito.dp2.WF.sol4.server.datamanager.ElementConverter;
import it.polito.dp2.WF.sol4.server.reference.Reference;

import javax.xml.ws.Endpoint;
import java.util.concurrent.Executors;

public class WorkflowServer {

    static final DataManager DATA_MANAGER = ElementConverter.init();

    public static void main(String[] args) {

        Endpoint info = Endpoint.create(new WorkflowInfoService());
        info.setExecutor(Executors.newFixedThreadPool(Reference.THREAD_NUMBER));
        info.publish("http://localhost:7071/wfinfo");

        Endpoint control = Endpoint.create(new WorkflowControlService());
        control.setExecutor(Executors.newFixedThreadPool(Reference.THREAD_NUMBER));
        control.publish("http://localhost:7070/wfcontrol");

    }

}
