package it.polito.dp2.WF.sol4.server.datamanager;


import it.polito.dp2.WF.lab4.gen.ActionStatusType;
import it.polito.dp2.WF.lab4.gen.ActorType;
import it.polito.dp2.WF.lab4.gen.ProcessType;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.regexp.internal.recompile;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

public class ProcessHolder {

    private GregorianCalendar lastMod;
    private ProcessType process;

    public ProcessHolder(ProcessType process) {
        this.lastMod = new GregorianCalendar();
        this.process = process;
    }

    public GregorianCalendar getLastMod() {
        return lastMod;
    }

    public ProcessType getProcess() {
    	synchronized (this) {
    		return (new ProcessCopier(process)).getProcess();
		}
    	
    }
    
    public ProcessType getProcessNoTSafe() {
        return process;
    }

    public void setProcess(ProcessType process) {
        lastMod = new GregorianCalendar();
        this.process = process;
    }
    
    private class ProcessCopier{
    	private final ProcessType process;
    	
    	public ProcessCopier(ProcessType process){
    		this.process = new ProcessType();
    		this.process.setWorkflow(process.getWorkflow());
    		XMLGregorianCalendar date;
    		
    		 try {
				date = DatatypeFactory.newInstance().newXMLGregorianCalendar(process.getDate().toGregorianCalendar());
			} catch (DatatypeConfigurationException e) {
				//e.printStackTrace();
				date = process.getDate();
			}
    		 
    		 this.process.setDate(date);
    		 
    		 for(ActionStatusType action: process.getActionStatus()){
    			 this.process.getActionStatus().add((new ActionStatusCopier(action)).getAction());
    			 
    		 }
    	}
    	
    	
    	public ProcessType getProcess(){
    		
    		return process;
    	}
    	
    	
    	private class ActionStatusCopier{
    		private  ActionStatusType actionStatus;
    		
    		public ActionStatusCopier(ActionStatusType actionStatus){
    			this.actionStatus = new ActionStatusType();
    			XMLGregorianCalendar date;
    			
    			 try {
    				 date = DatatypeFactory.newInstance().newXMLGregorianCalendar(actionStatus.getDate().toGregorianCalendar());
    				 this.actionStatus.setDate(date);    				 
				} catch (DatatypeConfigurationException e) {
					//e.printStackTrace();
					date = actionStatus.getDate();
				}
    			 this.actionStatus.setDate(date);
    			 this.actionStatus.setName(actionStatus.getName());
    			 this.actionStatus.setTakenInCharge(actionStatus.isTakenInCharge());
    			 this.actionStatus.setTerminated(actionStatus.isTerminated());
    			 
    			 this.actionStatus.setActor((new ActorCopier(actionStatus.getActor()).getActor()));
    			 
    			
    		}
    		
    		public ActionStatusType getAction() {
    				return actionStatus;
			}
    		
    		private class ActorCopier{
    			private ActorType actor;
    			
    			public ActorCopier(ActorType actor){
    				if(actor!= null){
    				this.actor = new ActorType();
    				this.actor.setName(actor.getName());
    				this.actor.setRole(actor.getRole());
    				}
    				this.actor = null;
    			}
    			
    			public ActorType getActor() {
    				return actor;
					
				}
    			
    		}
    		
    	}
    	
    	
    }

}
