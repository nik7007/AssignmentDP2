package it.polito.dp2.WF.sol4.server;


import it.polito.dp2.WF.sol4.server.datamanager.DataManager;
import it.polito.dp2.WF.sol4.server.datamanager.ElementConverter;
import it.polito.dp2.WF.sol4.server.reference.Reference;

import javax.xml.ws.Endpoint;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkflowServer {

    static final DataManager DATA_MANAGER = ElementConverter.init();
    private static Logger logger = Logger.getLogger(WorkflowServer.class.getName());

    public static void main(String[] args) {

        Endpoint info = Endpoint.create(new WorkflowInfoService());
        info.setExecutor(Executors.newFixedThreadPool(Reference.THREAD_NUMBER));


        Endpoint control = Endpoint.create(new WorkflowControlService());
        control.setExecutor(Executors.newFixedThreadPool(Reference.THREAD_NUMBER));

        try {
            info.publish("http://localhost:7071/wfinfo");
            control.publish("http://localhost:7070/wfcontrol");

        } catch (IllegalArgumentException | IllegalStateException e) {
            logger.log(Level.SEVERE, "Error publishing services");
        }

    }

}
