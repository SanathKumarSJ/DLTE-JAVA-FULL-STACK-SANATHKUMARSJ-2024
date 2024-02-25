package basics.service;

import java.util.ResourceBundle;

public class MyCardDateException extends RuntimeException {
    public MyCardDateException(){
        super(ResourceBundle.getBundle("application").getString("exception.date"));
    }
}
