
package it.polito.dp2.WF.lab4.gen;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This interface provides only operations to get all the processes and workflow from the serve
 *         
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WorkflowInfoInterface", targetNamespace = "http://www.example.org/Workflow/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WorkflowInfoInterface {


    /**
     * This operation allows the client to get the name of all the workflow in the server.
     * Each workflow is characterized by its name.
     * The sever does not only send a list of names but also a date which indicates the last time that the
     * entire list was modified
     *             
     * 
     * @param parameters
     * @return
     *     returns it.polito.dp2.WF.lab4.gen.GetWorkflowNameResponseType
     */
    @WebMethod(action = "http://www.example.org/Workflow/getInfoWorkflowName")
    @WebResult(name = "getWorkflowNameResponse", targetNamespace = "http://www.example.org/Workflow/", partName = "getInfoWorkflowNameResponse")
    public GetWorkflowNameResponseType getInfoWorkflowName(
        @WebParam(name = "getWorkflowName", targetNamespace = "http://www.example.org/Workflow/", partName = "parameters")
        GetWorkflowName parameters);

    /**
     * With the parameter "getProcessName" the client sends the the server a list of
     *  workflow names and the server will respond with a sequence of processes active for each workflow.
     * Attached to each process there is also a date which indicates the last modification time.
     *             
     * 
     * @param parameters
     * @return
     *     returns it.polito.dp2.WF.lab4.gen.GetProcessNameResponse
     * @throws GetInfoProcessNameFault_Exception
     */
    @WebMethod(action = "http://www.example.org/Workflow/getInfoProcessName")
    @WebResult(name = "getProcessNameResponse", targetNamespace = "http://www.example.org/Workflow/", partName = "getInfoProcessNameResponse")
    public GetProcessNameResponse getInfoProcessName(
        @WebParam(name = "getProcessName", targetNamespace = "http://www.example.org/Workflow/", partName = "parameters")
        GetProcessName parameters)
        throws GetInfoProcessNameFault_Exception
    ;

    /**
     *  The server receives a sequence of workflow's names and send to the client all the workflow
     * corresponding to those names.
     *  Attached to each workflow there is also a date which indicates the last modification time.
     *             
     * 
     * @param parameters
     * @return
     *     returns it.polito.dp2.WF.lab4.gen.GetWorkflow
     * @throws GetInfoWorkflowFault_Exception
     */
    @WebMethod(action = "http://www.example.org/Workflow/getInfoWorkflow")
    @WebResult(name = "getWorkflow", targetNamespace = "http://www.example.org/Workflow/", partName = "getInfoWorkflowResponse")
    public GetWorkflow getInfoWorkflow(
        @WebParam(name = "getWorkflowByName", targetNamespace = "http://www.example.org/Workflow/", partName = "parameters")
        GetWorkflowByName parameters)
        throws GetInfoWorkflowFault_Exception
    ;

}
