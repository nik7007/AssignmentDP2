package it.polito.dp2.WF.sol1.workflow;

abstract class Action {
	
	private String name;
	private boolean auto;
	
	public  Action(String name, boolean auto) {
		
		this.auto = auto;
		this.name = name;
		
	}
	
	public String getName() {
		
		return name;		
	}
	
	public boolean isAuto() {
		return auto;
		
	}

}
