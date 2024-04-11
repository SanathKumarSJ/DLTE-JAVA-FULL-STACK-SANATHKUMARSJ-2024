package com.employee.dao.exception;

import java.util.ResourceBundle;

public class InvalidContactException extends RuntimeException{
    public InvalidContactException(String information) {
        super(information+ ResourceBundle.getBundle("validation").getString("info.wrong"));
    }
}
