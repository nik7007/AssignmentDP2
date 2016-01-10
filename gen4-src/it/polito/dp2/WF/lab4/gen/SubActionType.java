
package it.polito.dp2.WF.lab4.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per sub_actionType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
     * Recupera il valore della proprietà nameRef.
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
     * Imposta il valore della proprietà nameRef.
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
