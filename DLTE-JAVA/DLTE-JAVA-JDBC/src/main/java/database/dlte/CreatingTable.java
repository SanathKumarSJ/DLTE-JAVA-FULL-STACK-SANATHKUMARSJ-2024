package database.dlte;

import com.sun.org.apache.xpath.internal.operations.Or;
import oracle.jdbc.OracleDriver;

import java.sql.*;
import java.util.ResourceBundle;

public class CreatingTable {
    public static void main(String[] args) {
        // making connection
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(resourceBundle.getString("db.url"), ("db.user"), ("db.pass"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}