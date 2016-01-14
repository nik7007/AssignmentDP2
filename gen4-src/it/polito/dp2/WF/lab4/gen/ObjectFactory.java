
package it.polito.dp2.WF.lab4.gen;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.polito.dp2.WF.lab4.gen package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ResultOperation_QNAME = new QName("http://www.example.org/Workflow/", "resultOperation");
    private final static QName _WorkflowName_QNAME = new QName("http://www.example.org/Workflow/", "workflowName");
    private final static QName _TakeOverActionFault_QNAME = new QName("http://www.example.org/Workflow/", "takeOverActionFault");
    private final static QName _GetWorkflowNameResponse_QNAME = new QName("http://www.example.org/Workflow/", "getWorkflowNameResponse");
    private final static QName _Out_QNAME = new QName("http://www.example.org/Workflow/", "out");
    private final static QName _CreatProcessFault_QNAME = new QName("http://www.example.org/Workflow/", "creatProcessFault");
    private final static QName _ActionInProcess_QNAME = new QName("http://www.example.org/Workflow/", "actionInProcess");
    private final static QName _DataCreation_QNAME = new QName("http://www.example.org/Workflow/", "dataCreation");
    private final static QName _ActionToComplete_QNAME = new QName("http://www.example.org/Workflow/", "actionToComplete");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.polito.dp2.WF.lab4.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetWorkflow }
     * 
     */
    public GetWorkflow createGetWorkflow() {
        return new GetWorkflow();
    }

    /**
     * Create an instance of {@link GetProcessNameResponse }
     * 
     */
    public GetProcessNameResponse createGetProcessNameResponse() {
        return new GetProcessNameResponse();
    }

    /**
     * Create an instance of {@link ActionInProcessType }
     * 
     */
    public ActionInProcessType createActionInProcessType() {
        return new ActionInProcessType();
    }

    /**
     * Create an instance of {@link GetProcessName }
     * 
     */
    public GetProcessName createGetProcessName() {
        return new GetProcessName();
    }

    /**
     * Create an instance of {@link GetWorkflow.LastModTimeAndWorkflow }
     * 
     */
    public GetWorkflow.LastModTimeAndWorkflow createGetWorkflowLastModTimeAndWorkflow() {
        return new GetWorkflow.LastModTimeAndWorkflow();
    }

    /**
     * Create an instance of {@link GetInfoProcessNameFault }
     * 
     */
    public GetInfoProcessNameFault createGetInfoProcessNameFault() {
        return new GetInfoProcessNameFault();
    }

    /**
     * Create an instance of {@link CompleteActionFault }
     * 
     */
    public CompleteActionFault createCompleteActionFault() {
        return new CompleteActionFault();
    }

    /**
     * Create an instance of {@link CreatProcess }
     * 
     */
    public CreatProcess createCreatProcess() {
        return new CreatProcess();
    }

    /**
     * Create an instance of {@link ActionToCompleteType }
     * 
     */
    public ActionToCompleteType createActionToCompleteType() {
        return new ActionToCompleteType();
    }

    /**
     * Create an instance of {@link GetProcessNameResponse.ProcessAndTime }
     * 
     */
    public GetProcessNameResponse.ProcessAndTime createGetProcessNameResponseProcessAndTime() {
        return new GetProcessNameResponse.ProcessAndTime();
    }

    /**
     * Create an instance of {@link GetWorkflowName }
     * 
     */
    public GetWorkflowName createGetWorkflowName() {
        return new GetWorkflowName();
    }

    /**
     * Create an instance of {@link GetInfoWorkflowFault }
     * 
     */
    public GetInfoWorkflowFault createGetInfoWorkflowFault() {
        return new GetInfoWorkflowFault();
    }

    /**
     * Create an instance of {@link GetWorkflowNameResponseType }
     * 
     */
    public GetWorkflowNameResponseType createGetWorkflowNameResponseType() {
        return new GetWorkflowNameResponseType();
    }

    /**
     * Create an instance of {@link GetWorkflowByName }
     * 
     */
    public GetWorkflowByName createGetWorkflowByName() {
        return new GetWorkflowByName();
    }

    /**
     * Create an instance of {@link ActionStatusType }
     * 
     */
    public ActionStatusType createActionStatusType() {
        return new ActionStatusType();
    }

    /**
     * Create an instance of {@link ActionType }
     * 
     */
    public ActionType createActionType() {
        return new ActionType();
    }

    /**
     * Create an instance of {@link ActorType }
     * 
     */
    public ActorType createActorType() {
        return new ActorType();
    }

    /**
     * Create an instance of {@link RootType }
     * 
     */
    public RootType createRootType() {
        return new RootType();
    }

    /**
     * Create an instance of {@link WorkflowType }
     * 
     */
    public WorkflowType createWorkflowType() {
        return new WorkflowType();
    }

    /**
     * Create an instance of {@link ProcessActionType }
     * 
     */
    public ProcessActionType createProcessActionType() {
        return new ProcessActionType();
    }

    /**
     * Create an instance of {@link ProcessType }
     * 
     */
    public ProcessType createProcessType() {
        return new ProcessType();
    }

    /**
     * Create an instance of {@link SubActionType }
     * 
     */
    public SubActionType createSubActionType() {
        return new SubActionType();
    }

    /**
     * Create an instance of {@link SimpleActionType }
     * 
     */
    public SimpleActionType createSimpleActionType() {
        return new SimpleActionType();
    }

    /**
     * Create an instance of {@link ActionInProcessType.ProcessIdentifier }
     * 
     */
    public ActionInProcessType.ProcessIdentifier createActionInProcessTypeProcessIdentifier() {
        return new ActionInProcessType.ProcessIdentifier();
    }

    /**
     * Create an instance of {@link ActionInProcessType.Action }
     * 
     */
    public ActionInProcessType.Action createActionInProcessTypeAction() {
        return new ActionInProcessType.Action();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Workflow/", name = "resultOperation")
    public JAXBElement<Boolean> createResultOperation(Boolean value) {
        return new JAXBElement<Boolean>(_ResultOperation_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Workflow/", name = "workflowName")
    public JAXBElement<String> createWorkflowName(String value) {
        return new JAXBElement<String>(_WorkflowName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Workflow/", name = "takeOverActionFault")
    public JAXBElement<String> createTakeOverActionFault(String value) {
        return new JAXBElement<String>(_TakeOverActionFault_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkflowNameResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Workflow/", name = "getWorkflowNameResponse")
    public JAXBElement<GetWorkflowNameResponseType> createGetWorkflowNameResponse(GetWorkflowNameResponseType value) {
        return new JAXBElement<GetWorkflowNameResponseType>(_GetWorkflowNameResponse_QNAME, GetWorkflowNameResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Workflow/", name = "out")
    public JAXBElement<String> createOut(String value) {
        return new JAXBElement<String>(_Out_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Workflow/", name = "creatProcessFault")
    public JAXBElement<String> createCreatProcessFault(String value) {
        return new JAXBElement<String>(_CreatProcessFault_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActionInProcessType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Workflow/", name = "actionInProcess")
    public JAXBElement<ActionInProcessType> createActionInProcess(ActionInProcessType value) {
        return new JAXBElement<ActionInProcessType>(_ActionInProcess_QNAME, ActionInProcessType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Workflow/", name = "dataCreation")
    public JAXBElement<XMLGregorianCalendar> createDataCreation(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DataCreation_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActionToCompleteType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Workflow/", name = "actionToComplete")
    public JAXBElement<ActionToCompleteType> createActionToComplete(ActionToCompleteType value) {
        return new JAXBElement<ActionToCompleteType>(_ActionToComplete_QNAME, ActionToCompleteType.class, null, value);
    }

}
