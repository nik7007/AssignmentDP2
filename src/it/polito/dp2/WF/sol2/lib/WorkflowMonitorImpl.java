package it.polito.dp2.WF.sol2.lib;


import it.polito.dp2.WF.ProcessReader;
import it.polito.dp2.WF.WorkflowMonitor;
import it.polito.dp2.WF.WorkflowReader;
import it.polito.dp2.WF.sol2.jaxb.ProcessType;
import it.polito.dp2.WF.sol2.jaxb.RootType;
import it.polito.dp2.WF.sol2.jaxb.WorkflowType;
import it.polito.dp2.WF.sol2.reference.Reference;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.*;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

public class WorkflowMonitorImpl implements WorkflowMonitor {

    private Map<String, WorkflowReader> workflowReaderMap;
    private Map<Calendar, ProcessReader> processReaderMap;


    public WorkflowMonitorImpl() {
        workflowReaderMap = new HashMap<>();
        processReaderMap = new HashMap<>();
        String fileName = System.getProperty("it.polito.dp2.WF.sol2.WorkflowInfo.file");
        //System.err.println(fileName);

        RootType root = createRoot(fileName);
        List<WorkflowType> workflowTypes = root.getWorkflow();
        List<ProcessType> processTypes = root.getProcess();
    }


    RootType createRoot(String fileName) {

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Reference.bindingPackage);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);

            Schema schema = schemaFactory.newSchema(new File("..//xsd//WFInfo.xsd"));

            unmarshaller.setSchema(schema);


            JAXBElement<RootType> jaxbElementRoot = (JAXBElement<RootType>) unmarshaller.unmarshal(new File(fileName));

            return jaxbElementRoot.getValue();


        } catch (JAXBException | SAXException e) {
            e.printStackTrace();
        }


        return null;

    }


    @Override
    public Set<WorkflowReader> getWorkflows() {
        return new HashSet<>(workflowReaderMap.values());
    }

    @Override
    public WorkflowReader getWorkflow(String s) {

        if (workflowReaderMap.containsKey(s))
            return workflowReaderMap.get(s);
        else
            return null;
    }

    @Override
    public Set<ProcessReader> getProcesses() {
        return new HashSet<>(processReaderMap.values());
    }
}
