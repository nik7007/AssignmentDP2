
package it.polito.dp2.WF.lab3.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per action complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="action">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://pad.polito.it/WorkflowInfo}Name"/>
 *         &lt;element name="automaticallyInstantiated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="role" type="{http://pad.polito.it/WorkflowInfo}Role"/>
 *         &lt;choice>
 *           &lt;element name="nextAction" type="{http://pad.polito.it/WorkflowInfo}Name" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="workflow" type="{http://pad.polito.it/WorkflowInfo}Name" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "action", propOrder = {
    "name",
    "automaticallyInstantiated",
    "role",
    "nextAction",
    "workflow"
})
public class Action {

    @XmlElement(required = true)
    protected String name;
    protected boolean automaticallyInstantiated;
    @XmlElement(required = true)
    protected String role;
    @XmlElement(nillable = true)
    protected List<String> nextAction;
    protected String workflow;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietà automaticallyInstantiated.
     * 
     */
    public boolean isAutomaticallyInstantiated() {
        return automaticallyInstantiated;
    }

    /**
     * Imposta il valore della proprietà automaticallyInstantiated.
     * 
     */
    public void setAutomaticallyInstantiated(boolean value) {
        this.automaticallyInstantiated = value;
    }

    /**
     * Recupera il valore della proprietà role.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * Imposta il valore della proprietà role.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Gets the value of the nextAction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nextAction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNextAction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNextAction() {
        if (nextAction == null) {
            nextAction = new ArrayList<String>();
        }
        return this.nextAction;
    }

    /**
     * Recupera il valore della proprietà workflow.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflow() {
        return workflow;
    }

    /**
     * Imposta il valore della proprietà workflow.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflow(String value) {
        this.workflow = value;
    }

}
