package org.project1;

import java.util.ResourceBundle;

public class DriverException extends RuntimeException{
    public DriverException(){
        super(ResourceBundle.getBundle("application").getString("driver.error"));
    }
}
