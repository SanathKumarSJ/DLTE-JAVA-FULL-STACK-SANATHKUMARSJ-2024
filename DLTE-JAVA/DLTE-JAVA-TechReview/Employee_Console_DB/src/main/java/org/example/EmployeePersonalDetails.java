package org.example;

import java.io.Serializable;

public class EmployeePersonalDetails implements Serializable {
    private String firstNameOfEmployee;
    private String middleNameOfEmployee;
    private String lastNameOfEmployee;
    private Integer employeeID;
    private Long employeeContactNumber;
    private String employeeEmail;
//    private EmployeeAddressDetails employeePermanentAddress;
//    private EmployeeAddressDetails employeeTemporaryAddress;


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

    public EmployeePersonalDetails(String firstNameOfEmployee, String middleNameOfEmployee, String lastNameOfEmployee, Integer employeeID, Long employeeContactNumber, String employeeEmail) {
        this.firstNameOfEmployee = firstNameOfEmployee;
        this.middleNameOfEmployee = middleNameOfEmployee;
        this.lastNameOfEmployee = lastNameOfEmployee;
        this.employeeID = employeeID;
        this.employeeContactNumber = employeeContactNumber;
        this.employeeEmail = employeeEmail;
    }
}

