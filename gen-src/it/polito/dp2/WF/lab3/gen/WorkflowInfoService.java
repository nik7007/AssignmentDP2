
package it.polito.dp2.WF.lab3.gen;

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
@WebServiceClient(name = "WorkflowInfoService", targetNamespace = "http://pad.polito.it/WorkflowInfo", wsdlLocation = "http://localhost:8181/WorkflowInfoService?wsdl")
public class WorkflowInfoService
    extends Service
{

    private final static URL WORKFLOWINFOSERVICE_WSDL_LOCATION;
    private final static WebServiceException WORKFLOWINFOSERVICE_EXCEPTION;
    private final static QName WORKFLOWINFOSERVICE_QNAME = new QName("http://pad.polito.it/WorkflowInfo", "WorkflowInfoService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8181/WorkflowInfoService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WORKFLOWINFOSERVICE_WSDL_LOCATION = url;
        WORKFLOWINFOSERVICE_EXCEPTION = e;
    }

    public WorkflowInfoService() {
        super(__getWsdlLocation(), WORKFLOWINFOSERVICE_QNAME);
    }

    public WorkflowInfoService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WORKFLOWINFOSERVICE_QNAME, features);
    }

    public WorkflowInfoService(URL wsdlLocation) {
        super(wsdlLocation, WORKFLOWINFOSERVICE_QNAME);
    }

    public WorkflowInfoService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WORKFLOWINFOSERVICE_QNAME, features);
    }

    public WorkflowInfoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WorkflowInfoService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WorkflowInfo
     */
    @WebEndpoint(name = "WorkflowInfoPort")
    public WorkflowInfo getWorkflowInfoPort() {
        return super.getPort(new QName("http://pad.polito.it/WorkflowInfo", "WorkflowInfoPort"), WorkflowInfo.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WorkflowInfo
     */
    @WebEndpoint(name = "WorkflowInfoPort")
    public WorkflowInfo getWorkflowInfoPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://pad.polito.it/WorkflowInfo", "WorkflowInfoPort"), WorkflowInfo.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WORKFLOWINFOSERVICE_EXCEPTION!= null) {
            throw WORKFLOWINFOSERVICE_EXCEPTION;
        }
        return WORKFLOWINFOSERVICE_WSDL_LOCATION;
    }

}
