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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;


public class WFInfoSerializer {

    private WorkflowMonitor monitor;
    private DateFormat dateFormat;

    //xml stuff...
    private Document doc;    // document element
    private Element root;    // document root element

    public WFInfoSerializer() throws WorkflowMonitorException, ParserConfigurationException {

        it.polito.dp2.WF.WorkflowMonitorFactory factory = WorkflowMonitorFactory.newInstance();
        monitor = factory.newWorkflowMonitor();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        //DOM
        DocumentBuilderFactory factoryDOM = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factoryDOM.newDocumentBuilder();
        doc = builder.newDocument();
        CreateRoot("root");

    }

    private String createDate(Calendar calendar) {

        if (calendar != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            simpleDateFormat.setCalendar(calendar);
            simpleDateFormat.setTimeZone(calendar.getTimeZone());
            return simpleDateFormat.format(calendar.getTime());
        } else return "";

    }

    private void CreateRoot(String rootName) {
        root = doc.createElement(rootName);
        doc.appendChild(root);
    }

    public Element createWorkFlow(WorkflowReader wFR) {

        String name = wFR.getName();
        Set<ActionReader> actions = wFR.getActions();
        Element wokFlow = doc.createElement("workflow");
        wokFlow.setAttribute("name", name);

        /*
        Calendar calendar = null;

        for (ProcessReader processReader : getProcesses()) {
            if (processReader.getWorkflow().getName().equals(name)) {

                wokFlow.setAttribute("process", "true");

                calendar = processReader.getStartTime();
                break;
            }
        }

        if (calendar != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            simpleDateFormat.setCalendar(calendar);
            simpleDateFormat.setTimeZone(calendar.getTimeZone());
            wokFlow.setAttribute("date", simpleDateFormat.format(calendar.getTime()));
        }*/

        for (ActionReader actionReader : actions)
            wokFlow.appendChild(createAction(actionReader));
        return wokFlow;

    }

    private Element createAction(ActionReader actionReader) {
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

                action.appendChild(createSubAction(a));

            }

        } else {
            action.setAttribute("sub_workflow", ((ProcessActionReader) actionReader).getActionWorkflow().getName());
        }

        action.setAttribute("auto", (String.valueOf(actionReader.isAutomaticallyInstantiated())));


        return action;
    }

    private Element createSubAction(ActionReader actionReader) {
        Element subAction = doc.createElement("sub_action");

        subAction.setAttribute("name_ref", actionReader.getName());

        return subAction;
    }

    public Element createProcess(ProcessReader processReader) {
        Element process = doc.createElement("process");
        process.setAttribute("workflow", processReader.getWorkflow().getName());
        process.setAttribute("date", createDate(processReader.getStartTime()));

        for (ActionStatusReader actionStatusReader : processReader.getStatus()) {
            process.appendChild(createActionStatus(actionStatusReader));
        }

        return process;

    }

    private Element createActionStatus(ActionStatusReader actionStatusReader) {
        Element actionStatus = doc.createElement("action_status");

        actionStatus.setAttribute("name", actionStatusReader.getActionName());
        actionStatus.setAttribute("date", createDate(actionStatusReader.getTerminationTime()));
        actionStatus.setAttribute("taken_in_charge", String.valueOf(actionStatusReader.isTakenInCharge()));
        actionStatus.setAttribute("terminated", String.valueOf(actionStatusReader.isTerminated()));

        Actor actor = actionStatusReader.getActor();

        if (actor != null)
            actionStatus.appendChild(createActor(actor));

        return actionStatus;

    }

    private Element createActor(Actor actor) {

        if (actor == null)
            return null;
        Element actorElement = doc.createElement("actor");

        actorElement.setAttribute("name", actor.getName());
        actorElement.setAttribute("role", actor.getRole());

        return actorElement;
    }

    public void serialize(PrintStream out) throws TransformerException {

        TransformerFactory xformFactory = TransformerFactory.newInstance();
        Transformer idTransform;
        idTransform = xformFactory.newTransformer();
        idTransform.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMImplementation domImpl = doc.getImplementation();
        DocumentType doctype = domImpl.createDocumentType("body", "SYSTEM", "wfInfo.dtd");
        idTransform.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
        idTransform.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());


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


    public static void main(String[] args) throws WorkflowMonitorException, ParserConfigurationException, TransformerException, FileNotFoundException {

        WFInfoSerializer infoSerializer = new WFInfoSerializer();
        Set<WorkflowReader> workflowReaders = infoSerializer.getWorkflowReader();
        Set<ProcessReader> processReaders = infoSerializer.getProcesses();

        for (WorkflowReader reader : workflowReaders) {
            infoSerializer.root.appendChild(infoSerializer.createWorkFlow(reader));
        }
        for (ProcessReader processReader : processReaders) {
            infoSerializer.root.appendChild(infoSerializer.createProcess(processReader));
        }

        infoSerializer.serialize(System.out);

        String fileName = "${output}";

        if (fileName.equals("${output}")) {
            fileName = "output.xml";
        } else {
            char[] ext = {'.', 'x', 'm', 'l'};
            boolean extension = true;

            for (int i = 0; i < ext.length; i++) {
                char c = fileName.charAt(fileName.length() - 4 + i);

                if (c != ext[i]) {
                    extension = false;
                    break;
                }
            }

            if (!extension)
                fileName += ".xml";
        }

        infoSerializer.serialize(new PrintStream(new FileOutputStream("dtd//" + fileName)));

    }


}
