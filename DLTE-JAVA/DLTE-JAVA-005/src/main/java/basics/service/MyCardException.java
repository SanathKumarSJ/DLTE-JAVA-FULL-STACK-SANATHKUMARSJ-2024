package basics.service;

import java.util.ResourceBundle;

public class MyCardException extends RuntimeException {
    public MyCardException() {
        super(ResourceBundle.getBundle("application").getString("exception.credit"));
    }
}