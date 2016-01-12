package it.polito.dp2.WF.sol1;

import it.polito.dp2.WF.*;
import it.polito.dp2.WF.sol1.lib.SerializerException;
import it.polito.dp2.WF.sol1.reference.DateFormat;
import it.polito.dp2.WF.sol1.reference.XMLFormat;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;


public class WFInfoSerializer {

    private WorkflowMonitor monitor;

    //xml stuff...
    private Document doc;    // document element
    private Element root;    // document root element

    public WFInfoSerializer() throws WorkflowMonitorException, ParserConfigurationException {

        it.polito.dp2.WF.WorkflowMonitorFactory factory = WorkflowMonitorFactory.newInstance();
        monitor = factory.newWorkflowMonitor();

        //DOM
        DocumentBuilderFactory factoryDOM = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factoryDOM.newDocumentBuilder();
        doc = builder.newDocument();
        CreateRoot(XMLFormat.ELEM_ROOT.toString());

    }

    private String createDate(Calendar calendar) {

        if (calendar != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.DATE_FORMAT.toString());
            simpleDateFormat.setCalendar(calendar);
            simpleDateFormat.setTimeZone(calendar.getTimeZone());
            return simpleDateFormat.format(calendar.getTime());
        } else return "";

    }

    private void CreateRoot(String rootName) {
        root = doc.createElement(rootName);
        doc.appendChild(root);
    }

    public Element createWorkFlow(WorkflowReader wFR) throws SerializerException {

        String name = wFR.getName();
        Set<ActionReader> actions = wFR.getActions();
        Element wokFlow = doc.createElement(XMLFormat.ELEM_WORKFLOW.toString());
        wokFlow.setAttribute(XMLFormat.ATT_NAME.toString(), name);

        for (ActionReader actionReader : actions)
            wokFlow.appendChild(createAction(actionReader));
        return wokFlow;

    }

    private Element createAction(ActionReader actionReader) throws SerializerException {
        Element action;
        boolean isSimple = false;

        if (actionReader instanceof SimpleActionReader) {
            action = doc.createElement(XMLFormat.ELEM_SIMPLE_ACTION.toString());
            isSimple = true;
        } else if (actionReader instanceof ProcessActionReader)
            action = doc.createElement(XMLFormat.ELEM_PROCESS_ACTION.toString());
        else throw new SerializerException("\'" + actionReader.getClass() + "\' is not a valid class!");

        action.setAttribute(XMLFormat.ATT_NAME.toString(), actionReader.getName());
        action.setAttribute(XMLFormat.ATT_ROLE.toString(), actionReader.getRole());

        if (isSimple) {
            for (ActionReader a : ((SimpleActionReader) actionReader).getPossibleNextActions()) {

                action.appendChild(createSubAction(a));

            }

        } else {
            action.setAttribute(XMLFormat.ATT_SUB_WORKFLOW.toString(), ((ProcessActionReader) actionReader).getActionWorkflow().getName());
        }

        action.setAttribute(XMLFormat.ATT_AUTO.toString(), (String.valueOf(actionReader.isAutomaticallyInstantiated())));


        return action;
    }

    private Element createSubAction(ActionReader actionReader) {
        Element subAction = doc.createElement(XMLFormat.ELEM_SUB_ACTION.toString());

        subAction.setAttribute(XMLFormat.ATT_NAME_REF.toString(), actionReader.getName());

        return subAction;
    }

    public Element createProcess(ProcessReader processReader) {
        Element process = doc.createElement(XMLFormat.ELEM_PROCESS.toString());
        process.setAttribute(XMLFormat.ELEM_WORKFLOW.toString(), processReader.getWorkflow().getName());
        process.setAttribute(XMLFormat.ATT_DATE.toString(), createDate(processReader.getStartTime()));

        for (ActionStatusReader actionStatusReader : processReader.getStatus()) {
            process.appendChild(createActionStatus(actionStatusReader));
        }

        return process;

    }

    private Element createActionStatus(ActionStatusReader actionStatusReader) {
        Element actionStatus = doc.createElement(XMLFormat.ELEM_ACTION_STATUS.toString());

        actionStatus.setAttribute(XMLFormat.ATT_NAME.toString(), actionStatusReader.getActionName());
        String date = createDate(actionStatusReader.getTerminationTime());

        if (!date.equals(""))
            actionStatus.setAttribute(XMLFormat.ATT_DATE.toString(), date);
        actionStatus.setAttribute(XMLFormat.ATT_TAKEN_IN_CHARGE.toString(), String.valueOf(actionStatusReader.isTakenInCharge()));
        actionStatus.setAttribute(XMLFormat.ATT_TERMINATED.toString(), String.valueOf(actionStatusReader.isTerminated()));

        Actor actor = actionStatusReader.getActor();

        if (actor != null)
            actionStatus.appendChild(createActor(actor));

        return actionStatus;

    }

    private Element createActor(Actor actor) {

        if (actor == null)
            return null;
        Element actorElement = doc.createElement(XMLFormat.ELEM_ACTOR.toString());

        actorElement.setAttribute(XMLFormat.ATT_NAME.toString(), actor.getName());
        actorElement.setAttribute(XMLFormat.ATT_ROLE.toString(), actor.getRole());

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


    public static void main(String[] args) throws WorkflowMonitorException, ParserConfigurationException, TransformerException, FileNotFoundException, SerializerException {

        WFInfoSerializer infoSerializer = new WFInfoSerializer();
        Set<WorkflowReader> workflowReaders = infoSerializer.getWorkflowReader();
        Set<ProcessReader> processReaders = infoSerializer.getProcesses();

        for (WorkflowReader reader : workflowReaders) {
            infoSerializer.root.appendChild(infoSerializer.createWorkFlow(reader));
        }
        for (ProcessReader processReader : processReaders) {
            infoSerializer.root.appendChild(infoSerializer.createProcess(processReader));
        }

        //infoSerializer.serialize(System.out);

        String fileName = args[0];

        if (args.length <= 0) {
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

        infoSerializer.serialize(new PrintStream(new FileOutputStream(/*"dtd//" + */fileName)));

    }


}
