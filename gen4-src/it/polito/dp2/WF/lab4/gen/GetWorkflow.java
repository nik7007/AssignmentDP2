
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
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="lastModTimeAndWorkflow">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="lastModTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="workflow" type="{http://www.example.org/Workflow/}workflowType"/>
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
    "lastModTimeAndWorkflow"
})
@XmlRootElement(name = "getWorkflow")
public class GetWorkflow {

    protected List<GetWorkflow.LastModTimeAndWorkflow> lastModTimeAndWorkflow;

    /**
     * Gets the value of the lastModTimeAndWorkflow property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lastModTimeAndWorkflow property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLastModTimeAndWorkflow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetWorkflow.LastModTimeAndWorkflow }
     * 
     * 
     */
    public List<GetWorkflow.LastModTimeAndWorkflow> getLastModTimeAndWorkflow() {
        if (lastModTimeAndWorkflow == null) {
            lastModTimeAndWorkflow = new ArrayList<GetWorkflow.LastModTimeAndWorkflow>();
        }
        return this.lastModTimeAndWorkflow;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="lastModTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="workflow" type="{http://www.example.org/Workflow/}workflowType"/>
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
        "workflow"
    })
    public static class LastModTimeAndWorkflow {

        @XmlElement(required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar lastModTime;
        @XmlElement(required = true)
        protected WorkflowType workflow;

        /**
         * Recupera il valore della proprietà lastModTime.
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
         * Imposta il valore della proprietà lastModTime.
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
         * Recupera il valore della proprietà workflow.
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
         * Imposta il valore della proprietà workflow.
         * 
         * @param value
         *     allowed object is
         *     {@link WorkflowType }
         *     
         */
        public void setWorkflow(WorkflowType value) {
            this.workflow = value;
        }

    }

}
