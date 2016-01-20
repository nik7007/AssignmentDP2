
package it.polito.dp2.WF.lab4.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sub_actionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sub_actionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name_ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sub_actionType")
public class SubActionType {

    @XmlAttribute(name = "name_ref")
    protected String nameRef;

    /**
     * Gets the value of the nameRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameRef() {
        return nameRef;
    }

    /**
     * Sets the value of the nameRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameRef(String value) {
        this.nameRef = value;
    }

}
