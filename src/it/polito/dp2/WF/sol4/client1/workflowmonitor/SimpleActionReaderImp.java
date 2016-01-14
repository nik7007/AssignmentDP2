package it.polito.dp2.WF.sol4.client1.workflowmonitor;


import it.polito.dp2.WF.ActionReader;
import it.polito.dp2.WF.SimpleActionReader;
import it.polito.dp2.WF.WorkflowReader;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SimpleActionReaderImp extends ActionReaderImp implements SimpleActionReader {

    private List<ActionReader> actionReaders;

    public SimpleActionReaderImp(String name, WorkflowReader enclosingWorkflow, String role, boolean automaticallyInstantiated) {
        super(name, enclosingWorkflow, role, automaticallyInstantiated);
        actionReaders = new ArrayList<>();
    }

    public void addActionReader(ActionReader actionReader) {
        actionReaders.add(actionReader);
    }

    @Override
    public Set<ActionReader> getPossibleNextActions() {
        return new LinkedHashSet<>(actionReaders);
    }
}
