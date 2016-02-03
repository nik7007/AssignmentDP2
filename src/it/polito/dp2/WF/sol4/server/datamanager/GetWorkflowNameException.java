package it.polito.dp2.WF.sol4.server.datamanager;

public class GetWorkflowNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public GetWorkflowNameException(String wfName){
		super(wfName + " does not exist!");
		message = wfName + " does not exist!";
		
	}
	
	public String getMessage(){
		
		return message;
	}
	

}
