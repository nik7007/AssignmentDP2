
package it.polito.dp2.WF.lab4.gen;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This interface provides only the operation to change the data on the server.
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WorkflowControlInterface", targetNamespace = "http://www.example.org/Workflow/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WorkflowControlInterface {


    /**
     * Given a existing workflow name this operation allows the client to create a process on the server
     * 
     * @param parameters
     * @return
     *     returns javax.xml.datatype.XMLGregorianCalendar
     * @throws CreateProcessFault
     */
    @WebMethod(action = "http://www.example.org/Workflow/createProcess")
    @WebResult(name = "dataCreation", targetNamespace = "http://www.example.org/Workflow/", partName = "createProcessResponse")
    public XMLGregorianCalendar createProcess(
        @WebParam(name = "workflowName", targetNamespace = "http://www.example.org/Workflow/", partName = "parameters")
        String parameters)
        throws CreateProcessFault
    ;

    /**
     * This operation allows to take over an action in one of the active process identify an action in a process and an actor.
     * 
     * @param takeOverActionRequest
     * @return
     *     returns boolean
     * @throws TakeOverActionFault
     */
    @WebMethod(action = "http://www.example.org/Workflow/takeOverAction")
    @WebResult(name = "resultOperation", targetNamespace = "http://www.example.org/Workflow/", partName = "takeOverActionResponse")
    public boolean takeOverAction(
        @WebParam(name = "actionInProcess", targetNamespace = "http://www.example.org/Workflow/", partName = "takeOverActionRequest")
        ActionInProcessType takeOverActionRequest)
        throws TakeOverActionFault
    ;

    /**
     * Selected an action in a process this operation will terminated and start a new one indicated by the client.
     * 
     * @param completeActionRequest
     * @return
     *     returns boolean
     * @throws CompleteActionFault_Exception
     */
    @WebMethod(action = "http://www.example.org/Workflow/completeAction")
    @WebResult(name = "resultOperation", targetNamespace = "http://www.example.org/Workflow/", partName = "completeActionResponse")
    public boolean completeAction(
        @WebParam(name = "actionToComplete", targetNamespace = "http://www.example.org/Workflow/", partName = "completeActionRequest")
        ActionToCompleteType completeActionRequest)
        throws CompleteActionFault_Exception
    ;

}
