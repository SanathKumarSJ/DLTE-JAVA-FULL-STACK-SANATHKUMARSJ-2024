
package services.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for employeePersonal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employeePersonal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="firstNameOfEmployee" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="middleNameOfEmployee" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastNameOfEmployee" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="employeeID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="employeeContactNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="employeeEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="employeePermanentAddress" type="{http://employee.services}employeeAddress"/>
 *         &lt;element name="employeeTemporaryAddress" type="{http://employee.services}employeeAddress"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employeePersonal", propOrder = {
    "firstNameOfEmployee",
    "middleNameOfEmployee",
    "lastNameOfEmployee",
    "employeeID",
    "employeeContactNumber",
    "employeeEmail",
    "employeePermanentAddress",
    "employeeTemporaryAddress"
})
public class EmployeePersonal {

    @XmlElement(required = true)
    protected String firstNameOfEmployee;
    @XmlElement(required = true)
    protected String middleNameOfEmployee;
    @XmlElement(required = true)
    protected String lastNameOfEmployee;
    protected int employeeID;
    protected long employeeContactNumber;
    @XmlElement(required = true)
    protected String employeeEmail;
    @XmlElement(required = true)
    protected EmployeeAddress employeePermanentAddress;
    @XmlElement(required = true)
    protected EmployeeAddress employeeTemporaryAddress;

    /**
     * Gets the value of the firstNameOfEmployee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstNameOfEmployee() {
        return firstNameOfEmployee;
    }

    /**
     * Sets the value of the firstNameOfEmployee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstNameOfEmployee(String value) {
        this.firstNameOfEmployee = value;
    }

    /**
     * Gets the value of the middleNameOfEmployee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleNameOfEmployee() {
        return middleNameOfEmployee;
    }

    /**
     * Sets the value of the middleNameOfEmployee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleNameOfEmployee(String value) {
        this.middleNameOfEmployee = value;
    }

    /**
     * Gets the value of the lastNameOfEmployee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastNameOfEmployee() {
        return lastNameOfEmployee;
    }

    /**
     * Sets the value of the lastNameOfEmployee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastNameOfEmployee(String value) {
        this.lastNameOfEmployee = value;
    }

    /**
     * Gets the value of the employeeID property.
     * 
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the value of the employeeID property.
     * 
     */
    public void setEmployeeID(int value) {
        this.employeeID = value;
    }

    /**
     * Gets the value of the employeeContactNumber property.
     * 
     */
    public long getEmployeeContactNumber() {
        return employeeContactNumber;
    }

    /**
     * Sets the value of the employeeContactNumber property.
     * 
     */
    public void setEmployeeContactNumber(long value) {
        this.employeeContactNumber = value;
    }

    /**
     * Gets the value of the employeeEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeEmail() {
        return employeeEmail;
    }

    /**
     * Sets the value of the employeeEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeEmail(String value) {
        this.employeeEmail = value;
    }

    /**
     * Gets the value of the employeePermanentAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddress }
     *     
     */
    public EmployeeAddress getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    /**
     * Sets the value of the employeePermanentAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddress }
     *     
     */
    public void setEmployeePermanentAddress(EmployeeAddress value) {
        this.employeePermanentAddress = value;
    }

    /**
     * Gets the value of the employeeTemporaryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddress }
     *     
     */
    public EmployeeAddress getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    /**
     * Sets the value of the employeeTemporaryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddress }
     *     
     */
    public void setEmployeeTemporaryAddress(EmployeeAddress value) {
        this.employeeTemporaryAddress = value;
    }

}
