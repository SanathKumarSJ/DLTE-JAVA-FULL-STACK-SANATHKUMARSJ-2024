//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.04.11 at 08:17:35 PM IST 
//


package services.employee;

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
 *         &lt;element name="employeePersonal" type="{http://employee.services}employeePersonal"/>
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
    "employeePersonal"
})
@XmlRootElement(name = "newEmployeeRequest")
public class NewEmployeeRequest {

    @XmlElement(required = true)
    protected EmployeePersonal employeePersonal;

    /**
     * Gets the value of the employeePersonal property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeePersonal }
     *     
     */
    public EmployeePersonal getEmployeePersonal() {
        return employeePersonal;
    }

    /**
     * Sets the value of the employeePersonal property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeePersonal }
     *     
     */
    public void setEmployeePersonal(EmployeePersonal value) {
        this.employeePersonal = value;
    }

}
