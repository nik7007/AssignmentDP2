package it.polito.dp2.WF.sol4.server.datamanager;


import it.polito.dp2.WF.lab4.gen.WorkflowType;

import java.util.GregorianCalendar;

public class WorkflowHolder {
    private GregorianCalendar lastMod;
    private WorkflowType workflow;

    public WorkflowHolder(WorkflowType workflow) {
        this.lastMod = new GregorianCalendar();
        this.workflow = workflow;
    }

    public GregorianCalendar getLastMod() {
        return lastMod;
    }

    public WorkflowType getWorkflow() {
        return workflow;
    }

    public void setWorkflow(WorkflowType workflow) {
        lastMod = new GregorianCalendar();
        this.workflow = workflow;
    }
}
