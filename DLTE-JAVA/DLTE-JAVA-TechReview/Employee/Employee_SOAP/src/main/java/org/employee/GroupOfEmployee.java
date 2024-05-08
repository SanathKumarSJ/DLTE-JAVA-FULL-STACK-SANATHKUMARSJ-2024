package org.employee;

import org.backend.EmployeePersonalDetails;

import java.util.ArrayList;
import java.util.List;

public class GroupOfEmployee {
    private List<EmployeePersonalDetails> employeePersonalDetails;

    public GroupOfEmployee() {
    }

    @Override
    public String toString() {
        return "GroupOfEmployee{" +
                "employeePersonalDetails=" + employeePersonalDetails +
                '}';
    }

    public GroupOfEmployee(List<EmployeePersonalDetails> employeePersonalDetails) {
        this.employeePersonalDetails = employeePersonalDetails;
    }

    public List<EmployeePersonalDetails> getEmployeePersonalDetails() {
        return employeePersonalDetails;
    }

    public void setEmployeePersonalDetails(List<EmployeePersonalDetails> employeePersonalDetails) {
        this.employeePersonalDetails = employeePersonalDetails;
    }
}
