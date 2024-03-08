package org.exceptionlog;

import java.util.ResourceBundle;

public class MyBankException extends RuntimeException{
    public MyBankException(String string){
        super(ResourceBundle.getBundle("application").getString("gpay.error"));
    }

    public MyBankException() {
        super(ResourceBundle.getBundle("application").getString("gpay.error"));
    }
}
