package com.employee.dao.exception;

import java.util.ResourceBundle;

public class UserExist extends RuntimeException{
    public UserExist (){
        super(ResourceBundle.getBundle("database").getString("user.exist"));
    }
}
