package it.polito.dp2.WF.sol1.workflow;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Workflow {
	
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
	
}
