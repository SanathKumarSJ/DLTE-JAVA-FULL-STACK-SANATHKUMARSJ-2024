package org.employee.console.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static Boolean isValidUsername(String username) {
        String usernameExpression = "^[A-Za-z]\\w{4,29}$";
        Pattern pattern = Pattern.compile(usernameExpression);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static Boolean isValidLastUsername(String username) {
        String usernameExpression = "^[A-Za-z]\\w{1,29}$";
        Pattern pattern = Pattern.compile(usernameExpression);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static Boolean isValidEmail(String borrowerEmail) {
        String emailExpression = "^[A-Za-z0-9+_.-]+@[a-zA-Z]{3,}+\\.[a-z]{2,}";
        Pattern pattern = Pattern.compile(emailExpression);
        Matcher matcher = pattern.matcher(borrowerEmail);
        return matcher.matches();
    }

    public static Boolean isValidID(Integer idnumber) {
        String contactString = Integer.toString(idnumber);
        String mobileExpression = "^(?!0{5})\\d{5}$";
        Pattern pattern = Pattern.compile(mobileExpression);
        Matcher matcher = pattern.matcher(contactString);
        return matcher.matches();
    }

    public static Boolean isValidContactNumber(Long contactNumber) {
        String contactString = Long.toString(contactNumber);
        String mobileExpression = "^(?!0{10})\\d{10}$";
        Pattern pattern = Pattern.compile(mobileExpression);
        Matcher matcher = pattern.matcher(contactString);
        return matcher.matches();
    }

    public static Boolean isValidPin(Integer pin) {
        String contactString = Integer.toString(pin);
        String pinExpression = "^(?!0{6})\\d{6}$";
        Pattern pattern = Pattern.compile(pinExpression);
        Matcher matcher = pattern.matcher(contactString);
        return matcher.matches();
    }
}
