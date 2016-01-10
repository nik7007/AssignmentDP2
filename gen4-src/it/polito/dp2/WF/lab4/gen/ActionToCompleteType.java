
package it.polito.dp2.WF.lab4.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per actionToCompleteType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="actionToCompleteType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/Workflow/}actionInProcessType">
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="sub_action" type="{http://www.example.org/Workflow/}sub_actionType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actionToCompleteType", propOrder = {
    "subAction"
})
public class ActionToCompleteType
    extends ActionInProcessType
{

    @XmlElement(name = "sub_action")
    protected List<SubActionType> subAction;

    /**
     * Gets the value of the subAction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subAction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubAction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubActionType }
     * 
     * 
     */
    public List<SubActionType> getSubAction() {
        if (subAction == null) {
            subAction = new ArrayList<SubActionType>();
        }
        return this.subAction;
    }

}
