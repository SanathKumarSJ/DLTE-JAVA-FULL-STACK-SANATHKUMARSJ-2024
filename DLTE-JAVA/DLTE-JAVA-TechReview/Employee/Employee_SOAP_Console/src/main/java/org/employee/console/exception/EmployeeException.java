package org.employee.console.exception;

import java.util.ResourceBundle;

public class EmployeeException extends RuntimeException {
    public EmployeeException() {
        super(ResourceBundle.getBundle("application").getString("app.error"));
    }

    public EmployeeException(String disp) {
        super(ResourceBundle.getBundle("application").getString("app.disp.error"));
    }
}