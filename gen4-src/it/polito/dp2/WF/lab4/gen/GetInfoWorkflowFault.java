
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
 *         &lt;element name="getInfoWorkflowFault" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getInfoWorkflowFault"
})
@XmlRootElement(name = "getInfoWorkflowFault")
public class GetInfoWorkflowFault {

    @XmlElement(required = true)
    protected String getInfoWorkflowFault;

    /**
     * Gets the value of the getInfoWorkflowFault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetInfoWorkflowFault() {
        return getInfoWorkflowFault;
    }

    /**
     * Sets the value of the getInfoWorkflowFault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetInfoWorkflowFault(String value) {
        this.getInfoWorkflowFault = value;
    }

}
