package org.exception;

import java.util.ResourceBundle;

public class ConnectionException extends RuntimeException {
    public ConnectionException() {
        super(ResourceBundle.getBundle("database").getString("no.data"));
    }
}
