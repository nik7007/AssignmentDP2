
package it.polito.dp2.WF.lab4.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per process_actionType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="process_actionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/Workflow/}actionType">
 *       &lt;attribute name="sub_workflow" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    protected String subWorkflow;

    /**
     * Recupera il valore della proprietà subWorkflow.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubWorkflow() {
        return subWorkflow;
    }

    /**
     * Imposta il valore della proprietà subWorkflow.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubWorkflow(String value) {
        this.subWorkflow = value;
    }

}
