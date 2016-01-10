
package it.polito.dp2.WF.lab4.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
     * Recupera il valore della proprietà getInfoProcessNameFault.
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
     * Imposta il valore della proprietà getInfoProcessNameFault.
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
