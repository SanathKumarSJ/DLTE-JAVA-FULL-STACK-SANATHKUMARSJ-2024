package com.employee.dao.exception;

public class EmployeeNotFoundException extends  RuntimeException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

