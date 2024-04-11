package com.employee.exception;

import java.util.ResourceBundle;

public class UserExist extends RuntimeException{
    public UserExist (){
        super(ResourceBundle.getBundle("database").getString("user.exist"));
    }
}
