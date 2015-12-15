//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.12.15 alle 10:40:53 PM CET 
//


package it.polito.dp2.WF.sol2.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per process_actionType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="process_actionType">
 *   &lt;complexContent>
 *     &lt;extension base="{}actionType">
 *       &lt;attribute name="sub_workflow" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "process_actionType")
public class ProcessActionType
    extends ActionType
{

    @XmlAttribute(name = "sub_workflow", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object subWorkflow;

    /**
     * Recupera il valore della proprietà subWorkflow.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSubWorkflow() {
        return subWorkflow;
    }

    /**
     * Imposta il valore della proprietà subWorkflow.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSubWorkflow(Object value) {
        this.subWorkflow = value;
    }

}
