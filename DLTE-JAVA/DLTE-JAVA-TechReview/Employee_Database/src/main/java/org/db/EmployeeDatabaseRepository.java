package org.db;

import oracle.jdbc.driver.OracleDriver;
import org.example.EmployeeAddressDetails;
import org.example.EmployeeFile;
import org.example.EmployeePersonalDetails;
import org.example.EmplyeeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeDatabaseRepository{
    static Logger logger = LoggerFactory.getLogger(EmployeeDatabaseRepository.class);
    private static ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    Driver driver=new OracleDriver();
    Connection connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
    private ResultSet resultSet;
    public EmployeeDatabaseRepository() throws SQLException {
    }

    public void input(EmployeePersonalDetails employeePersonalDetails) throws SQLException {
        System.out.println("inside add");
        System.out.println(employeePersonalDetails);
        try {
            System.out.println(employeePersonalDetails);
            DriverManager.registerDriver(driver);
            String query =  "insert into EmployeePersonal values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeePersonalDetails.getFirstNameOfEmployee());
            preparedStatement.setString(2, employeePersonalDetails.getMiddleNameOfEmployee());
            preparedStatement.setString(3, employeePersonalDetails.getLastNameOfEmployee());
            preparedStatement.setInt(4, employeePersonalDetails.getEmployeeID());
            preparedStatement.setLong(5, employeePersonalDetails.getEmployeeContactNumber());
            preparedStatement.setString(6, employeePersonalDetails.getEmployeeEmail());

//create table EmployeePersonal(FirstNameOfEmployee varchar(255) not null, MiddleNameOfEmployee varchar(255) not null, LastNameOfEmployee varchar(255) not null,EmployeeID number primary key, EmployeeContactNumber number not null, EmployeeEmail varchar(255) not null);
            int result = preparedStatement.executeUpdate();
            if (result!=0) {
                logger.info(resourceBundle.getString("employee.push.ok"));
                System.out.println(resourceBundle.getString("employee.push.ok"));
            }
            else {
                logger.info(resourceBundle.getString("employee.push.fail"));
                System.out.println(resourceBundle.getString("employee.push.fail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void display(){
        System.out.println("inside display");
        try {
            String query = "select * from EmployeePersonal";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement=connection.prepareStatement(query);
            resultSet=preparedStatement.executeQuery();
            employeeList=new ArrayList<>();
            EmployeePersonalDetails personalDetails = null;
            while (resultSet.next()) {
                personalDetails = new EmployeePersonalDetails();
                personalDetails.setFirstNameOfEmployee(resultSet.getString(1));
                personalDetails.setMiddleNameOfEmployee(resultSet.getString(2));
                personalDetails.setLastNameOfEmployee(resultSet.getString(3));
                personalDetails.setEmployeeID(resultSet.getInt(4));
                personalDetails.setEmployeeContactNumber(resultSet.getLong(5));
                personalDetails.setEmployeeEmail(resultSet.getString(6));
                employeeList.add(personalDetails);
            }
            System.out.println(employeeList);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
//    private Connection connection;
//    private List<EmployeePersonalDetails> employeeList = new ArrayList<>();
//    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
//    private Logger logger = LoggerFactory.getLogger("EmployeeDatabaseRepository.class");
//
//    private PreparedStatement preparedStatement;
//    private ResultSet resultSet;
//
//    public EmployeeDatabaseRepository(Connection connection) {
//            this.connection=connection;
//    }
//
//    public EmployeeDatabaseRepository() {
//
//    }
//
//    @Override
//    public void input(EmployeePersonalDetails employeePersonalDetails) {
//        System.out.println(employeePersonalDetails);
//        try {
//            String query =  "insert into EmployeePersonal values(?,?,?,?,?,?)";
//            preparedStatement = connection.prepareStatement(query);
//
//
//            preparedStatement.setString(1, employeePersonalDetails.getFirstNameOfEmployee());
//            preparedStatement.setString(2, employeePersonalDetails.getMiddleNameOfEmployee());
//            preparedStatement.setString(3, employeePersonalDetails.getLastNameOfEmployee());
//            preparedStatement.setInt(4, employeePersonalDetails.getEmployeeID());
//            preparedStatement.setLong(5, employeePersonalDetails.getEmployeeContactNumber());
//            preparedStatement.setString(6, employeePersonalDetails.getEmployeeEmail());
//
////create table EmployeePersonal(FirstNameOfEmployee varchar(255) not null, MiddleNameOfEmployee varchar(255) not null, LastNameOfEmployee varchar(255) not null,EmployeeID number primary key, EmployeeContactNumber number not null, EmployeeEmail varchar(255) not null);
//            int result = preparedStatement.executeUpdate();
//            if (result!=0) {
//                logger.info(resourceBundle.getString("employee.push.ok"));
//                System.out.println(resourceBundle.getString("employee.push.ok"));
//            }
//            else {
//                logger.info(resourceBundle.getString("employee.push.fail"));
//                System.out.println(resourceBundle.getString("employee.push.fail"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public EmployeeAddressDetails inputAdd() {
//        return null;
//    }
//
//    @Override
//    public void display() {
//        System.out.println("inside display");
//        try {
//            String query = "select * from EmployeePersonal";
//            preparedStatement=connection.prepareStatement(query);
//            resultSet=preparedStatement.executeQuery();
//            employeeList=new ArrayList<>();
//            EmployeePersonalDetails personalDetails = null;
//            while (resultSet.next()) {
//                personalDetails = new EmployeePersonalDetails();
//                personalDetails.setFirstNameOfEmployee(resultSet.getString(1));
//                personalDetails.setMiddleNameOfEmployee(resultSet.getString(2));
//                personalDetails.setLastNameOfEmployee(resultSet.getString(3));
//                personalDetails.setEmployeeID(resultSet.getInt(4));
//                personalDetails.setEmployeeContactNumber(resultSet.getLong(5));
//                personalDetails.setEmployeeEmail(resultSet.getString(6));
//                employeeList.add(personalDetails);
//            }
//            System.out.println(employeeList);
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
//
//    @Override
//    public void search() throws IOException {
//
//    }
//}
