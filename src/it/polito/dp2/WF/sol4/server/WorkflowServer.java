package it.polito.dp2.WF.sol4.server;


import javax.xml.ws.Endpoint;

public class WorkflowServer {

    public static void main(String[] args) {

        Endpoint.publish("http://localhost:7071/wfinfo", new WorkflowInfoService());
        Endpoint.publish("http://localhost:7070/wfcontrol", new WorkflowControlService());

    }

}
