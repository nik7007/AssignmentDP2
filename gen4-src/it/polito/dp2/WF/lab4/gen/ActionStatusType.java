
package it.polito.dp2.WF.lab4.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per action_statusType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="action_statusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actor" type="{http://www.example.org/Workflow/}actorType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="taken_in_charge" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="terminated" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "action_statusType", propOrder = {
    "actor"
})
public class ActionStatusType {

    protected ActorType actor;
    @XmlAttribute(name = "date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "taken_in_charge")
    protected Boolean takenInCharge;
    @XmlAttribute(name = "terminated")
    protected Boolean terminated;

    /**
     * Recupera il valore della proprietÓ actor.
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
     * Imposta il valore della proprietÓ actor.
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
     * Recupera il valore della proprietÓ date.
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
     * Imposta il valore della proprietÓ date.
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
     * Recupera il valore della proprietÓ name.
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
     * Imposta il valore della proprietÓ name.
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
     * Recupera il valore della proprietÓ takenInCharge.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isTakenInCharge() {
        if (takenInCharge == null) {
            return false;
        } else {
            return takenInCharge;
        }
    }

    /**
     * Imposta il valore della proprietÓ takenInCharge.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTakenInCharge(Boolean value) {
        this.takenInCharge = value;
    }

    /**
     * Recupera il valore della proprietÓ terminated.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isTerminated() {
        if (terminated == null) {
            return false;
        } else {
            return terminated;
        }
    }

    /**
     * Imposta il valore della proprietÓ terminated.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTerminated(Boolean value) {
        this.terminated = value;
    }

}
