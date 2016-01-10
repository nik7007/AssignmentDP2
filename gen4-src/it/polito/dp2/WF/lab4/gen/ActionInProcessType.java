
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
 * <p>Classe Java per actionInProcessType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
     * Recupera il valore della proprietà processIdentifier.
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
     * Imposta il valore della proprietà processIdentifier.
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
     * Recupera il valore della proprietà action.
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
     * Imposta il valore della proprietà action.
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
     * Recupera il valore della proprietà actor.
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
     * Imposta il valore della proprietà actor.
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
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
         * Recupera il valore della proprietà actionName.
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
         * Imposta il valore della proprietà actionName.
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
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
         * Recupera il valore della proprietà date.
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
         * Imposta il valore della proprietà date.
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
         * Recupera il valore della proprietà workflow.
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
         * Imposta il valore della proprietà workflow.
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
