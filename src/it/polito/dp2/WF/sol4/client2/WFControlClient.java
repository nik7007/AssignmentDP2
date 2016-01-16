package it.polito.dp2.WF.sol4.client2;


import it.polito.dp2.WF.lab4.gen.CreateProcessFault;
import it.polito.dp2.WF.lab4.gen.WorkflowControllnterface;
import it.polito.dp2.WF.lab4.gen.WorkflowService;

import java.net.MalformedURLException;
import java.net.URL;

public class WFControlClient {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.exit(2);
        }

        WorkflowControllnterface proxy;
        String url = args[0];
        String workflowName = args[1];

        WorkflowService service;

        try {
            service = new WorkflowService(new URL(url));
            proxy = service.getWorkflowControl();
            proxy.createProcess(workflowName);

        } catch (MalformedURLException e) {
            System.exit(2);
        } catch (CreateProcessFault createProcessFault) {
            createProcessFault.printStackTrace();
            System.exit(1);
        }
        System.exit(0);

    }

}
