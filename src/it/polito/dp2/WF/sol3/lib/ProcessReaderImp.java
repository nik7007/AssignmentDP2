package it.polito.dp2.WF.sol3.lib;

import it.polito.dp2.WF.ActionStatusReader;
import it.polito.dp2.WF.ProcessReader;
import it.polito.dp2.WF.WorkflowReader;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class ProcessReaderImp implements ProcessReader {

    private Calendar startTime;
    private WorkflowReader workflowReader;
    private List<ActionStatusReader> status;

    public ProcessReaderImp(Calendar startTime, WorkflowReader workflowReader) throws WorkflowReaderException {
        this.startTime = startTime;
        this.workflowReader = workflowReader;
        status = new LinkedList<>();
        ((WorkflowReaderImp) this.workflowReader).addProcessReader(this);
    }

    public boolean addActionStatusReader(ActionStatusReader statusReader) {
        return status.add(statusReader);
    }

    @Override
    public Calendar getStartTime() {
        return (Calendar) startTime.clone();
    }

    @Override
    public WorkflowReader getWorkflow() {
        return workflowReader;
    }

    @Override
    public List<ActionStatusReader> getStatus() {
        return new LinkedList<>(this.status);
    }
}
