
package it.polito.dp2.WF.lab4.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="workflow" type="{http://www.example.org/Workflow/}workflowType"/>
 *         &lt;element name="processAndTime" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="lastModTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="process" type="{http://www.example.org/Workflow/}processType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "workflow",
    "processAndTime"
})
@XmlRootElement(name = "getProcessNameResponse")
public class GetProcessNameResponse {

    protected WorkflowType workflow;
    protected List<GetProcessNameResponse.ProcessAndTime> processAndTime;

    /**
     * Gets the value of the workflow property.
     * 
     * @return
     *     possible object is
     *     {@link WorkflowType }
     *     
     */
    public WorkflowType getWorkflow() {
        return workflow;
    }

    /**
     * Sets the value of the workflow property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkflowType }
     *     
     */
    public void setWorkflow(WorkflowType value) {
        this.workflow = value;
    }

    /**
     * Gets the value of the processAndTime property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processAndTime property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessAndTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetProcessNameResponse.ProcessAndTime }
     * 
     * 
     */
    public List<GetProcessNameResponse.ProcessAndTime> getProcessAndTime() {
        if (processAndTime == null) {
            processAndTime = new ArrayList<GetProcessNameResponse.ProcessAndTime>();
        }
        return this.processAndTime;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="lastModTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="process" type="{http://www.example.org/Workflow/}processType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "lastModTime",
        "process"
    })
    public static class ProcessAndTime {

        @XmlElement(required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar lastModTime;
        @XmlElement(required = true)
        protected ProcessType process;

        /**
         * Gets the value of the lastModTime property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getLastModTime() {
            return lastModTime;
        }

        /**
         * Sets the value of the lastModTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setLastModTime(XMLGregorianCalendar value) {
            this.lastModTime = value;
        }

        /**
         * Gets the value of the process property.
         * 
         * @return
         *     possible object is
         *     {@link ProcessType }
         *     
         */
        public ProcessType getProcess() {
            return process;
        }

        /**
         * Sets the value of the process property.
         * 
         * @param value
         *     allowed object is
         *     {@link ProcessType }
         *     
         */
        public void setProcess(ProcessType value) {
            this.process = value;
        }

    }

}
