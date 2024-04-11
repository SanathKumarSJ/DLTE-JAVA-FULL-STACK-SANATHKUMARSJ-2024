package com.employee.dao.exception;

import java.util.ResourceBundle;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String information) {
        super(information+ ResourceBundle.getBundle("validation").getString("info.wrong"));
    }
}
