package org.backend;

import oracle.jdbc.driver.OracleDriver;
import org.exception.ConnectionException;

import java.sql.*;
import java.util.ResourceBundle;

public class DriverInitializer{
    private static ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    private Connection connection;
    public Connection makeConnection() {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
        try {
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(resourceBundle.getString("db.url"), resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
            return connection;
        }catch (ConnectionException | SQLException con){
            throw new ConnectionException();
        }

    }
}
