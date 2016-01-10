
package it.polito.dp2.WF.lab4.gen;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WorkflowService", targetNamespace = "http://www.example.org/Workflow/", wsdlLocation = "file:/C:/IdeaProjects/AssignmentDP2/wsdl/Workflow.wsdl")
public class WorkflowService
    extends Service
{

    private final static URL WORKFLOWSERVICE_WSDL_LOCATION;
    private final static WebServiceException WORKFLOWSERVICE_EXCEPTION;
    private final static QName WORKFLOWSERVICE_QNAME = new QName("http://www.example.org/Workflow/", "WorkflowService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/IdeaProjects/AssignmentDP2/wsdl/Workflow.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WORKFLOWSERVICE_WSDL_LOCATION = url;
        WORKFLOWSERVICE_EXCEPTION = e;
    }

    public WorkflowService() {
        super(__getWsdlLocation(), WORKFLOWSERVICE_QNAME);
    }

    public WorkflowService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WORKFLOWSERVICE_QNAME, features);
    }

    public WorkflowService(URL wsdlLocation) {
        super(wsdlLocation, WORKFLOWSERVICE_QNAME);
    }

    public WorkflowService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WORKFLOWSERVICE_QNAME, features);
    }

    public WorkflowService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WorkflowService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WorkflowControllnterface
     */
    @WebEndpoint(name = "WorkflowControl")
    public WorkflowControllnterface getWorkflowControl() {
        return super.getPort(new QName("http://www.example.org/Workflow/", "WorkflowControl"), WorkflowControllnterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WorkflowControllnterface
     */
    @WebEndpoint(name = "WorkflowControl")
    public WorkflowControllnterface getWorkflowControl(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.example.org/Workflow/", "WorkflowControl"), WorkflowControllnterface.class, features);
    }

    /**
     * 
     * @return
     *     returns WorkflowInfoInterface
     */
    @WebEndpoint(name = "WorkflowInfo")
    public WorkflowInfoInterface getWorkflowInfo() {
        return super.getPort(new QName("http://www.example.org/Workflow/", "WorkflowInfo"), WorkflowInfoInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WorkflowInfoInterface
     */
    @WebEndpoint(name = "WorkflowInfo")
    public WorkflowInfoInterface getWorkflowInfo(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.example.org/Workflow/", "WorkflowInfo"), WorkflowInfoInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WORKFLOWSERVICE_EXCEPTION!= null) {
            throw WORKFLOWSERVICE_EXCEPTION;
        }
        return WORKFLOWSERVICE_WSDL_LOCATION;
    }

}
