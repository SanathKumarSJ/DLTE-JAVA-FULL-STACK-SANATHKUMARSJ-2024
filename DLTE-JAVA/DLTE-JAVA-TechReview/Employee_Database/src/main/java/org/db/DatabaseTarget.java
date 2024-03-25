//package org.db;
//
//import oracle.jdbc.OracleDriver;
//import org.example.EmplyeeInterface;
//
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//public class DatabaseTarget {
//
//    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
//    private Connection connection;
//
//    public DatabaseTarget() {
//
//        try {
//            Driver driver = new OracleDriver();
//            DriverManager.registerDriver(driver);
//            connection = DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public EmplyeeInterface getEmployeeInterface() {
////        return new EmployeeDatabaseRepository(connection);
////    }
////}
//    }}
