
package it.polito.dp2.WF.lab4.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="getInfoProcessNameFault" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getInfoProcessNameFault"
})
@XmlRootElement(name = "getInfoProcessNameFault")
public class GetInfoProcessNameFault {

    @XmlElement(required = true)
    protected String getInfoProcessNameFault;

    /**
     * Gets the value of the getInfoProcessNameFault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetInfoProcessNameFault() {
        return getInfoProcessNameFault;
    }

    /**
     * Sets the value of the getInfoProcessNameFault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetInfoProcessNameFault(String value) {
        this.getInfoProcessNameFault = value;
    }

}
