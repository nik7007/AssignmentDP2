package it.polito.dp2.WF.sol1;

import it.polito.dp2.WF.*;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;


public class WFInfoSerializer {

    private WorkflowMonitor monitor;
    private DateFormat dateFormat;

    //xml stuff...
    private Document doc;    // document element
    private Element root;    // document root element

    public WFInfoSerializer() throws WorkflowMonitorException, ParserConfigurationException {

        WorkflowMonitorFactory factory = WorkflowMonitorFactory.newInstance();
        monitor = factory.newWorkflowMonitor();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        //DOM
        DocumentBuilderFactory factoryDOM = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factoryDOM.newDocumentBuilder();
        doc = builder.newDocument();
        DOMImplementation domImpl = doc.getImplementation();
        DocumentType doctype = domImpl.createDocumentType("wfInfo", "SYSTEM", "wfInfo.dtd");
        doc.appendChild(doctype);


        CreateRoot("root");

    }

    private void CreateRoot(String rootName) {
        root = doc.createElement(rootName);
        doc.appendChild(root);
    }

    public Element CreatWorkFlow(WorkflowReader wFR) {

        String name = wFR.getName();
        Set<ActionReader> actions = wFR.getActions();
        Element wokFlow = doc.createElement("workflow");
        wokFlow.setAttribute("name", name);

        for (ProcessReader processReader : getProcesses()) {
            if (processReader.getWorkflow().getName().equals(name)) {

                wokFlow.setAttribute("process", "true");
                break;
            }
        }

        for (ActionReader actionReader : actions)
            wokFlow.appendChild(CreatAction(actionReader));
        return wokFlow;

    }

    private Element CreatAction(ActionReader actionReader) {
        Element action;
        boolean isSimple = false;

        if (actionReader instanceof SimpleActionReader) {
            action = doc.createElement("simple_action");
            isSimple = true;
        } else
            action = doc.createElement("process_action");

        action.setAttribute("name", actionReader.getName());
        action.setAttribute("role", actionReader.getRole());

        if (isSimple) {
            for (ActionReader a : ((SimpleActionReader) actionReader).getPossibleNextActions()) {

                action.appendChild(CreatSubAction(a));

            }

        } else {
            action.setAttribute("sub_workflow", ((ProcessActionReader) actionReader).getActionWorkflow().getName());
        }

        action.setAttribute("auto", (Boolean.valueOf(actionReader.isAutomaticallyInstantiated())).toString());


        return action;
    }

    private Element CreatSubAction(ActionReader actionReader) {
        Element subAction = doc.createElement("sub_action");

        subAction.setAttribute("name_ref", actionReader.getName());

        return subAction;
    }

    public void serialize(PrintStream out) throws TransformerException {

        TransformerFactory xformFactory = TransformerFactory.newInstance();
        Transformer idTransform;
        idTransform = xformFactory.newTransformer();
        idTransform.setOutputProperty(OutputKeys.INDENT, "yes");
        Source input = new DOMSource(doc);
        Result output = new StreamResult(out);
        idTransform.transform(input, output);
    }

    public Set<WorkflowReader> getWorkflowReader() {
        return monitor.getWorkflows();
    }

    public Set<ProcessReader> getProcesses() {
        return monitor.getProcesses();
    }


    public static void main(String[] args) throws WorkflowMonitorException, ParserConfigurationException, TransformerException {

        WFInfoSerializer infoSerializer = new WFInfoSerializer();
        Set<WorkflowReader> workflowReaders = infoSerializer.getWorkflowReader();

        for (WorkflowReader reader : workflowReaders) {
            infoSerializer.root.appendChild(infoSerializer.CreatWorkFlow(reader));
        }

        infoSerializer.serialize(System.out);

    }


}
