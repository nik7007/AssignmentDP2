
package it.polito.dp2.WF.lab3.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per getWorkflowsResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getWorkflowsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lastModTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="workflow" type="{http://pad.polito.it/WorkflowInfo}workflow" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getWorkflowsResponse", propOrder = {
    "lastModTime",
    "workflow"
})
public class GetWorkflowsResponse {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModTime;
    protected List<Workflow> workflow;

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
     * Gets the value of the workflow property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the workflow property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWorkflow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Workflow }
     * 
     * 
     */
    public List<Workflow> getWorkflow() {
        if (workflow == null) {
            workflow = new ArrayList<Workflow>();
        }
        return this.workflow;
    }

}
