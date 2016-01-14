package it.polito.dp2.WF.sol4.client1.workflowmonitor;


import it.polito.dp2.WF.ProcessActionReader;
import it.polito.dp2.WF.WorkflowReader;

public class ProcessActionReaderImp extends ActionReaderImp implements ProcessActionReader {

    private WorkflowReader workflow;

    public ProcessActionReaderImp(String name, WorkflowReader enclosingWorkflow, String role, boolean automaticallyInstantiated, WorkflowReader workflow) {
        super(name, enclosingWorkflow, role, automaticallyInstantiated);
        this.workflow = workflow;
    }

    @Override
    public WorkflowReader getActionWorkflow() {
        return workflow;
    }
}
