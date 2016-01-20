
package it.polito.dp2.WF.lab4.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for action_statusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the name property.
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
     * Sets the value of the name property.
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
     * Gets the value of the takenInCharge property.
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
     * Sets the value of the takenInCharge property.
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
     * Gets the value of the terminated property.
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
     * Sets the value of the terminated property.
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
