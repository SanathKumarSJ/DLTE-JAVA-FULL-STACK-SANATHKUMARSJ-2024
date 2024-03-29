package org.backend.entity;

import java.io.Serializable;

public class EmployeePersonalDetails implements Serializable {
    private String firstNameOfEmployee;
    private String middleNameOfEmployee;
    private String lastNameOfEmployee;
    private Integer employeeID;
    private Long employeeContactNumber;
    private String employeeEmail;
    private EmployeeAddressDetails permanentAddress;
    private EmployeeAddressDetails temporaryAddress;


    public EmployeePersonalDetails() {

    }

    @Override
    public String toString() {
        return "EmployeePersonalDetails{" +
                "firstNameOfEmployee='" + firstNameOfEmployee + '\'' +
                ", middleNameOfEmployee='" + middleNameOfEmployee + '\'' +
                ", lastNameOfEmployee='" + lastNameOfEmployee + '\'' +
                ", employeeID=" + employeeID +
                ", employeeContactNumber=" + employeeContactNumber +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", permanentAddress=" + permanentAddress +
                ", temporaryAddress=" + temporaryAddress +
                '}';
    }

    public String getFirstNameOfEmployee() {
        return firstNameOfEmployee;
    }

    public void setFirstNameOfEmployee(String firstNameOfEmployee) {
        this.firstNameOfEmployee = firstNameOfEmployee;
    }

    public String getMiddleNameOfEmployee() {
        return middleNameOfEmployee;
    }

    public void setMiddleNameOfEmployee(String middleNameOfEmployee) {
        this.middleNameOfEmployee = middleNameOfEmployee;
    }

    public String getLastNameOfEmployee() {
        return lastNameOfEmployee;
    }

    public void setLastNameOfEmployee(String lastNameOfEmployee) {
        this.lastNameOfEmployee = lastNameOfEmployee;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Long getEmployeeContactNumber() {
        return employeeContactNumber;
    }

    public void setEmployeeContactNumber(Long employeeContactNumber) {
        this.employeeContactNumber = employeeContactNumber;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public EmployeeAddressDetails getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(EmployeeAddressDetails permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public EmployeeAddressDetails getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(EmployeeAddressDetails temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public EmployeePersonalDetails(String firstNameOfEmployee, String middleNameOfEmployee, String lastNameOfEmployee, Integer employeeID, Long employeeContactNumber, String employeeEmail) {
        this.firstNameOfEmployee = firstNameOfEmployee;
        this.middleNameOfEmployee = middleNameOfEmployee;
        this.lastNameOfEmployee = lastNameOfEmployee;
        this.employeeID = employeeID;
        this.employeeContactNumber = employeeContactNumber;
        this.employeeEmail = employeeEmail;
    }

    public EmployeePersonalDetails(String firstNameOfEmployee, String middleNameOfEmployee, String lastNameOfEmployee, Integer employeeID, Long employeeContactNumber, String employeeEmail, EmployeeAddressDetails permanentAddress, EmployeeAddressDetails temporaryAddress) {
        this.firstNameOfEmployee = firstNameOfEmployee;
        this.middleNameOfEmployee = middleNameOfEmployee;
        this.lastNameOfEmployee = lastNameOfEmployee;
        this.employeeID = employeeID;
        this.employeeContactNumber = employeeContactNumber;
        this.employeeEmail = employeeEmail;
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
    }
}

