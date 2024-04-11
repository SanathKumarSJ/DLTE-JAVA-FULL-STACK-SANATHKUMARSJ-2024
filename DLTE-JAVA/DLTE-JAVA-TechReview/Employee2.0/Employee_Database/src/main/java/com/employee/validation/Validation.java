package com.employee.validation;

import com.employee.entity.EmployeePersonalDetails;
import com.employee.exception.InvalidContactException;
import com.employee.exception.InvalidUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    String validate=null;
    static ResourceBundle resourceBundle= ResourceBundle.getBundle("validation");
    private static Logger logger= LoggerFactory.getLogger(Validation.class);

    public String validateEmployee(EmployeePersonalDetails employee){
    if(!validateEmail(employee.getEmployeeEmail())){
        logger.info(resourceBundle.getString("validation.email"));
        validate="email";
        throw new InvalidContactException("email");
//        return "email";

    }
        if(!validatePhone(employee.getEmployeeContactNumber())) {
        logger.info(resourceBundle.getString("validation.phone"));
        validate="phone";
        throw new InvalidContactException("phone number");
//            return "phone";
    }
        if(!validateEmployeeId(employee.getEmployeeID())){
        logger.info(resourceBundle.getString("validation.id"));
        validate="id";
        throw new InvalidContactException("Employee id");
//            return "id";
    }
        if(!validateName(employee.getFirstNameOfEmployee())||!validateName(employee.getMiddleNameOfEmployee())||!validateName(employee.getLastNameOfEmployee()) ){
        logger.info(resourceBundle.getString("validation.name"));
        validate="name";
        throw new InvalidUserException("Name");
//        return "name";
    }
        if(!validateAdress(employee.getPermanentAddress().getHouseName())){
        logger.info(resourceBundle.getString("validation.house"));
        validate="house";
        throw new InvalidUserException("House name");
//        return "house";
    }
        if(!validateAdress(employee.getPermanentAddress().getCity())){
        logger.info(resourceBundle.getString("validation.city"));
        validate="city";
        throw new InvalidUserException("City name");
//        return "city";/
    }
        if(!validateAdress(employee.getPermanentAddress().getState())){
        logger.info(resourceBundle.getString("validation.state"));
        validate="state";
        throw new InvalidUserException("State name");
//        return "state";
    }
        if(!validateAdress(employee.getPermanentAddress().getStreetName())){
        logger.info(resourceBundle.getString("validation.street"));
        validate="street";
        throw new InvalidUserException("Street name");
//        return "street";
    }
        if(!validatePincode(employee.getPermanentAddress().getPincode()))
    {
        logger.info(resourceBundle.getString("validation.pin"));
//        validate="pin";
        throw new InvalidUserException("Pincode");
//        return "pin";
    }
        logger.info(resourceBundle.getString("validation.done"));
        validate=null;
        return validate;
}

    public boolean validateName(String anyName){
        String nameRegex = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(anyName);
        if (matcher.matches()) {
            // logger.info(resourceBundle.getString("email.validation"));
            return true;
        } else {
            //logger.info(resourceBundle.getString("email.not.validated"));
            return false;
        }
    }
    public boolean validateAdress(String data){
        if(data.length()!=0){
            return true;
        }
        else{
            return false;
        }

    }


    public boolean
    validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+-_]{3,}@[A-Za-z]{3,}(.)[A-Za-z]{2,}";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            // logger.info(resourceBundle.getString("email.validation"));
            return true;
        } else {
            //logger.info(resourceBundle.getString("email.not.validated"));
            return false;
        }
    }
    //validate phone number
    public boolean validatePhone(Long phone){
        String phoneRegex = "^[0-9]{10}";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phone.toString());
        if (matcher.matches()) {
            //   logger.info(resourceBundle.getString("phone.validation"));
            return true;

        } else {
            // logger.info(resourceBundle.getString("phone.not.validated"));
            return false;
        }

    }
    public boolean validatePincode(int pin){
        String pinRegex = "^[0-9]{6}";
        Pattern pattern = Pattern.compile(pinRegex);
        Matcher matcher = pattern.matcher(String.valueOf(pin));
        if (matcher.matches()) {
            //logger.info(resourceBundle.getString("pin.validated"));
            return true;

        } else {
            //logger.info(resourceBundle.getString("pin.not.validated"));
            return false;
        }
    }
    public boolean validateEmployeeId(int empId){
        String empIdRegex = "^[0-9]{5}";
        Pattern pattern = Pattern.compile(empIdRegex);
        Matcher matcher = pattern.matcher(String.valueOf(empId));
        if (matcher.matches()) {
            //logger.info(resourceBundle.getString("pin.validated"));
            return true;

        } else {
            //logger.info(resourceBundle.getString("pin.not.validated"));
            return false;
        }
    }
}
