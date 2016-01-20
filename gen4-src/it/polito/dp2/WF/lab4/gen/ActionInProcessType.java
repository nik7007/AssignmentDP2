
package it.polito.dp2.WF.lab4.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for actionInProcessType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="actionInProcessType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="processIdentifier">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="date" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                 &lt;attribute name="workflow" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="action">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="actionName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="actor" type="{http://www.example.org/Workflow/}actorType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actionInProcessType", propOrder = {
    "processIdentifier",
    "action",
    "actor"
})
@XmlSeeAlso({
    ActionToCompleteType.class
})
public class ActionInProcessType {

    @XmlElement(required = true)
    protected ActionInProcessType.ProcessIdentifier processIdentifier;
    @XmlElement(required = true)
    protected ActionInProcessType.Action action;
    @XmlElement(required = true)
    protected ActorType actor;

    /**
     * Gets the value of the processIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ActionInProcessType.ProcessIdentifier }
     *     
     */
    public ActionInProcessType.ProcessIdentifier getProcessIdentifier() {
        return processIdentifier;
    }

    /**
     * Sets the value of the processIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionInProcessType.ProcessIdentifier }
     *     
     */
    public void setProcessIdentifier(ActionInProcessType.ProcessIdentifier value) {
        this.processIdentifier = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link ActionInProcessType.Action }
     *     
     */
    public ActionInProcessType.Action getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionInProcessType.Action }
     *     
     */
    public void setAction(ActionInProcessType.Action value) {
        this.action = value;
    }

    /**
     * Gets the value of the actor property.
     * 
     * @return
     *     possible object is
     *     {@link ActorType }
     *     
     */
    public ActorType getActor() {
        return actor;
    }

    /**
     * Sets the value of the actor property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActorType }
     *     
     */
    public void setActor(ActorType value) {
        this.actor = value;
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
     *       &lt;attribute name="actionName" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Action {

        @XmlAttribute(name = "actionName")
        protected String actionName;

        /**
         * Gets the value of the actionName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getActionName() {
            return actionName;
        }

        /**
         * Sets the value of the actionName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setActionName(String value) {
            this.actionName = value;
        }

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
     *       &lt;attribute name="date" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *       &lt;attribute name="workflow" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ProcessIdentifier {

        @XmlAttribute(name = "date", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar date;
        @XmlAttribute(name = "workflow", required = true)
        @XmlIDREF
        @XmlSchemaType(name = "IDREF")
        protected Object workflow;

        /**
         * Gets the value of the date property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDate() {
            return date;
        }

        /**
         * Sets the value of the date property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDate(XMLGregorianCalendar value) {
            this.date = value;
        }

        /**
         * Gets the value of the workflow property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getWorkflow() {
            return workflow;
        }

        /**
         * Sets the value of the workflow property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setWorkflow(Object value) {
            this.workflow = value;
        }

    }

}
