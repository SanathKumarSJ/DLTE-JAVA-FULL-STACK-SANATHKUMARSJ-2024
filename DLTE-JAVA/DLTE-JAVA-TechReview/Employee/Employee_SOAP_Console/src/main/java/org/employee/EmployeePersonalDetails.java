
package org.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for employeePersonalDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employeePersonalDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employeeContactNumber" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="employeeEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employeeID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="firstNameOfEmployee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastNameOfEmployee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="middleNameOfEmployee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="permanentAddress" type="{http://employee.org/}employeeAddressDetails" minOccurs="0"/>
 *         &lt;element name="temporaryAddress" type="{http://employee.org/}employeeAddressDetails" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employeePersonalDetails", propOrder = {
    "employeeContactNumber",
    "employeeEmail",
    "employeeID",
    "firstNameOfEmployee",
    "lastNameOfEmployee",
    "middleNameOfEmployee",
    "permanentAddress",
    "temporaryAddress"
})
public class EmployeePersonalDetails {

    protected Long employeeContactNumber;
    protected String employeeEmail;
    protected Integer employeeID;
    protected String firstNameOfEmployee;
    protected String lastNameOfEmployee;
    protected String middleNameOfEmployee;
    protected EmployeeAddressDetails permanentAddress;
    protected EmployeeAddressDetails temporaryAddress;

    public EmployeePersonalDetails(String firstNameOfEmployee, String middleNameOfEmployee, String lastNameOfEmployee, Integer employeeID, Long employeeContactNumber, String employeeEmail, EmployeeAddressDetails permenantAddress, EmployeeAddressDetails temporaryAddress) {
    }

    @Override
    public String toString() {
        return "EmployeePersonalDetails{" +
                "employeeContactNumber=" + employeeContactNumber +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeID=" + employeeID +
                ", firstNameOfEmployee='" + firstNameOfEmployee + '\'' +
                ", lastNameOfEmployee='" + lastNameOfEmployee + '\'' +
                ", middleNameOfEmployee='" + middleNameOfEmployee + '\'' +
                ", permanentAddress=" + permanentAddress +
                ", temporaryAddress=" + temporaryAddress +
                '}';
    }

    public EmployeePersonalDetails(String firstNameOfEmployee, String middleNameOfEmployee, String lastNameOfEmployee, Integer employeeID, Long employeeContactNumber, String employeeEmail, org.backend.EmployeeAddressDetails permenantAddress, org.backend.EmployeeAddressDetails temporaryAddress) {
    }

    public EmployeePersonalDetails() {

    }

    /**
     * Gets the value of the employeeContactNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEmployeeContactNumber() {
        return employeeContactNumber;
    }

    /**
     * Sets the value of the employeeContactNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEmployeeContactNumber(Long value) {
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
     * Gets the value of the employeeID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the value of the employeeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEmployeeID(Integer value) {
        this.employeeID = value;
    }

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
     * Gets the value of the permanentAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddressDetails }
     *     
     */
    public EmployeeAddressDetails getPermanentAddress() {
        return permanentAddress;
    }

    /**
     * Sets the value of the permanentAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddressDetails }
     *     
     */
    public void setPermanentAddress(EmployeeAddressDetails value) {
        this.permanentAddress = value;
    }

    /**
     * Gets the value of the temporaryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddressDetails }
     *     
     */
    public EmployeeAddressDetails getTemporaryAddress() {
        return temporaryAddress;
    }

    /**
     * Sets the value of the temporaryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddressDetails }
     *     
     */
    public void setTemporaryAddress(EmployeeAddressDetails value) {
        this.temporaryAddress = value;
    }

}
