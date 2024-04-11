package com.employee.dao.exception;

import java.util.ResourceBundle;

public class NoDataException extends RuntimeException {
    public NoDataException(String information) {
        super(information+ ResourceBundle.getBundle("data").getString("no.data"));
    }
}
