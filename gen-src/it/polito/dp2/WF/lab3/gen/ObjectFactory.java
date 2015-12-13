
package it.polito.dp2.WF.lab3.gen;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.polito.dp2.WF.lab3.gen package. 
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

    private final static QName _GetWorkflows_QNAME = new QName("http://pad.polito.it/WorkflowInfo", "getWorkflows");
    private final static QName _GetWorkflowNamesResponse_QNAME = new QName("http://pad.polito.it/WorkflowInfo", "getWorkflowNamesResponse");
    private final static QName _GetWorkflowNames_QNAME = new QName("http://pad.polito.it/WorkflowInfo", "getWorkflowNames");
    private final static QName _GetWorkflowsResponse_QNAME = new QName("http://pad.polito.it/WorkflowInfo", "getWorkflowsResponse");
    private final static QName _UnknownNames_QNAME = new QName("http://pad.polito.it/WorkflowInfo", "UnknownNames");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.polito.dp2.WF.lab3.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetWorkflows }
     * 
     */
    public GetWorkflows createGetWorkflows() {
        return new GetWorkflows();
    }

    /**
     * Create an instance of {@link GetWorkflowNamesResponse }
     * 
     */
    public GetWorkflowNamesResponse createGetWorkflowNamesResponse() {
        return new GetWorkflowNamesResponse();
    }

    /**
     * Create an instance of {@link GetWorkflowsResponse }
     * 
     */
    public GetWorkflowsResponse createGetWorkflowsResponse() {
        return new GetWorkflowsResponse();
    }

    /**
     * Create an instance of {@link GetWorkflowNames }
     * 
     */
    public GetWorkflowNames createGetWorkflowNames() {
        return new GetWorkflowNames();
    }

    /**
     * Create an instance of {@link UnknownNames }
     * 
     */
    public UnknownNames createUnknownNames() {
        return new UnknownNames();
    }

    /**
     * Create an instance of {@link Workflow }
     * 
     */
    public Workflow createWorkflow() {
        return new Workflow();
    }

    /**
     * Create an instance of {@link Action }
     * 
     */
    public Action createAction() {
        return new Action();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkflows }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pad.polito.it/WorkflowInfo", name = "getWorkflows")
    public JAXBElement<GetWorkflows> createGetWorkflows(GetWorkflows value) {
        return new JAXBElement<GetWorkflows>(_GetWorkflows_QNAME, GetWorkflows.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkflowNamesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pad.polito.it/WorkflowInfo", name = "getWorkflowNamesResponse")
    public JAXBElement<GetWorkflowNamesResponse> createGetWorkflowNamesResponse(GetWorkflowNamesResponse value) {
        return new JAXBElement<GetWorkflowNamesResponse>(_GetWorkflowNamesResponse_QNAME, GetWorkflowNamesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkflowNames }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pad.polito.it/WorkflowInfo", name = "getWorkflowNames")
    public JAXBElement<GetWorkflowNames> createGetWorkflowNames(GetWorkflowNames value) {
        return new JAXBElement<GetWorkflowNames>(_GetWorkflowNames_QNAME, GetWorkflowNames.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkflowsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pad.polito.it/WorkflowInfo", name = "getWorkflowsResponse")
    public JAXBElement<GetWorkflowsResponse> createGetWorkflowsResponse(GetWorkflowsResponse value) {
        return new JAXBElement<GetWorkflowsResponse>(_GetWorkflowsResponse_QNAME, GetWorkflowsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnknownNames }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://pad.polito.it/WorkflowInfo", name = "UnknownNames")
    public JAXBElement<UnknownNames> createUnknownNames(UnknownNames value) {
        return new JAXBElement<UnknownNames>(_UnknownNames_QNAME, UnknownNames.class, null, value);
    }

}
