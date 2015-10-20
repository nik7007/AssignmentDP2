package it.polito.dp2.WF.sol1.workflow;

import it.polito.dp2.WF.ActionReader;
import it.polito.dp2.WF.ProcessReader;
import it.polito.dp2.WF.WorkflowReader;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Workflow implements WorkflowReader {
	
	private String name;
	private boolean auto;
	private List<Action> actionList;
	
public Workflow(String name, boolean auto) {
	this.name = name;
	this.auto= auto;
	actionList = new LinkedList<>();	
}

public boolean addAction(Action action) {
	
	return actionList.add(action);
	
}

public Collection<Action> getActionList() {
	
	return actionList;
	
}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Set<ActionReader> getActions() {
		return null;
	}

	@Override
	public Set<ProcessReader> getProcesses() {
		return null;
	}

	@Override
	public ActionReader getAction(String s) {
		return null;
	}
}
