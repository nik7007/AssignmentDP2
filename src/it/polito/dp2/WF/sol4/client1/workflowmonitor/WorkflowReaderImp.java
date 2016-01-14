package it.polito.dp2.WF.sol4.client1.workflowmonitor;

import it.polito.dp2.WF.ActionReader;
import it.polito.dp2.WF.ProcessReader;

import java.util.*;

public class WorkflowReaderImp implements it.polito.dp2.WF.WorkflowReader {

    private String name;
    private Map<String, ActionReader> actionReaderMap;
    private Map<Calendar, ProcessReader> processReaderMap;

    public WorkflowReaderImp(String name) {
        this.name = name;
        actionReaderMap = new HashMap<>();
        processReaderMap = new HashMap<>();
    }

    public void addActionReader(ActionReader actionReader) throws WorkflowReaderException {

        String name = actionReader.getName();

        if (actionReaderMap.containsKey(name))
            throw new WorkflowReaderException("This ActionReader \'" + name + "\' is already present!");
        else actionReaderMap.put(name, actionReader);

    }

    public void addProcessReader(ProcessReader processReader) throws WorkflowReaderException {

        Calendar calendar = processReader.getStartTime();

        if (processReaderMap.containsKey(calendar))
            throw new WorkflowReaderException("This ProcessReader '\" + date.toString() + \"' is already present!");
        else
            processReaderMap.put(calendar, processReader);

    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<ActionReader> getActions() {
        return new HashSet<>(actionReaderMap.values());
    }

    @Override
    public Set<ProcessReader> getProcesses() {
        return new HashSet<>(processReaderMap.values());
    }

    @Override
    public ActionReader getAction(String s) {
        return actionReaderMap.get(s);
    }
}
