package it.polito.dp2.WF.sol3.lib;

import it.polito.dp2.WF.ActionReader;
import it.polito.dp2.WF.WorkflowReader;


public abstract class ActionReaderImp implements ActionReader {

    private String name;
    private WorkflowReader enclosingWorkflow;
    private String role;
    private boolean automaticallyInstantiated;

    public ActionReaderImp(String name, WorkflowReader enclosingWorkflow, String role, boolean automaticallyInstantiated) {
        this.name = name;
        this.enclosingWorkflow = enclosingWorkflow;
        this.role = role;
        this.automaticallyInstantiated = automaticallyInstantiated;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public WorkflowReader getEnclosingWorkflow() {
        return enclosingWorkflow;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public boolean isAutomaticallyInstantiated() {
        return automaticallyInstantiated;
    }
}
