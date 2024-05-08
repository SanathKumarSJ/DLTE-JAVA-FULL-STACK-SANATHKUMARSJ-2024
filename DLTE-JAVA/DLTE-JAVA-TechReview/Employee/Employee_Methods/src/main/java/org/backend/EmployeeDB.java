package org.backend;
import org.exception.ConnectionException;
import org.exception.EmployeeException;
import org.exception.NoDataException;
import org.exception.UserExist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeDB implements MyInterface {
    private Validation validation = new Validation();
    static Logger logger = LoggerFactory.getLogger(EmployeeDB.class);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    public EmployeeDB() {
    }

    @Override
    public String input(EmployeePersonalDetails employeePersonalDetails) throws SQLException {
        ResourceBundle resourceBundleOne = ResourceBundle.getBundle("database");
        String val=validation.validateEmployee(employeePersonalDetails);
        if(val!=null){
            return val;
        }
        try {
            DriverInitializer setConnection = new DriverInitializer();
            Connection connection = setConnection.makeConnection();
            try {
                String query = "insert into EMPLOYEEPERSONAL values(?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, employeePersonalDetails.getFirstNameOfEmployee());
                preparedStatement.setString(2, employeePersonalDetails.getMiddleNameOfEmployee());
                preparedStatement.setString(3, employeePersonalDetails.getLastNameOfEmployee());
                preparedStatement.setInt(4, employeePersonalDetails.getEmployeeID());
                preparedStatement.setLong(5, employeePersonalDetails.getEmployeeContactNumber());
                preparedStatement.setString(6, employeePersonalDetails.getEmployeeEmail());
                int record = preparedStatement.executeUpdate();

                String query1 = "insert into EMPLOYEEADDRESS(HOUSENAME,STREETNAME,CITY,STATENAME,PINCODE,EMPLOYEEID,ADDRESS_ID,ADDRESS_TYPE) values(?,?,?,?,?,?,ADDID_SEQ.nextval,0)";
                preparedStatement = connection.prepareStatement(query1);
                preparedStatement.setString(1, employeePersonalDetails.getPermanentAddress().getHouseName());
                preparedStatement.setString(2, employeePersonalDetails.getPermanentAddress().getStreetName());
                preparedStatement.setString(3, employeePersonalDetails.getPermanentAddress().getCity());
                preparedStatement.setString(4, employeePersonalDetails.getPermanentAddress().getState());
                preparedStatement.setInt(5, employeePersonalDetails.getPermanentAddress().getPincode());
                preparedStatement.setInt(6, employeePersonalDetails.getEmployeeID());
                int record1 = preparedStatement.executeUpdate();

                String query2 = "insert into EMPLOYEEADDRESS(HOUSENAME,STREETNAME,CITY,STATENAME,PINCODE,EMPLOYEEID,ADDRESS_ID,ADDRESS_TYPE) values(?,?,?,?,?,?,ADDID_SEQ.nextval,1)";
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setString(1, employeePersonalDetails.getTemporaryAddress().getHouseName());
                preparedStatement.setString(2, employeePersonalDetails.getTemporaryAddress().getStreetName());
                preparedStatement.setString(3, employeePersonalDetails.getTemporaryAddress().getCity());
                preparedStatement.setString(4, employeePersonalDetails.getTemporaryAddress().getState());
                preparedStatement.setInt(5, employeePersonalDetails.getTemporaryAddress().getPincode());
                preparedStatement.setInt(6, employeePersonalDetails.getEmployeeID());
                int record2 = preparedStatement.executeUpdate();

                if (record != 0 && record1 != 0 && record2 != 0) {
                    logger.info(resourceBundle.getString("employee.added"));
                    preparedStatement.close();
                    connection.close();
                    return "EXC000";
                }
                preparedStatement.close();
                connection.close();
                logger.info(resourceBundle.getString("employee.not.added"));
                return "EXC001";

            } catch (SQLIntegrityConstraintViolationException sql) {
                throw new UserExist();
            }
            } catch (ConnectionException exp) {
                throw new ConnectionException();
        }
    }

    public List<EmployeePersonalDetails> display() throws SQLException {
        List<EmployeePersonalDetails> employeeList = new ArrayList<>();
        try {
            DriverInitializer setConnection = new DriverInitializer();
            Connection connection = setConnection.makeConnection();
//            String query = "select e.FIRSTNAMEOFEMPLOYEE,e.MIDDLENAMEOFEMPLOYEE,e.LASTNAMEOFEMPLOYEE,e.EMPLOYEEID,e.EMPLOYEECONTACTNUMBER,e.EMPLOYEEEMAIL,t.HOUSENAME,t.STREETNAME,t.CITY,t.STATENAME,t.PINCODE from EMPLOYEEPERSONAL e join EMPLOYEEADDRESS t on e.EMPLOYEEID=t.EMPLOYEEID where t.ADDRESS_TYPE in (1,0)";
            String query="select e.FIRSTNAMEOFEMPLOYEE,e.MIDDLENAMEOFEMPLOYEE,e.LASTNAMEOFEMPLOYEE,e.EMPLOYEEID,e.EMPLOYEECONTACTNUMBER,e.EMPLOYEEEMAIL, p.HOUSENAME,p.STREETNAME,p.CITY,p.STATENAME,p.PINCODE, t.HOUSENAME,t.STREETNAME,t.CITY,t.STATENAME,t.PINCODE from EMPLOYEEPERSONAL e inner join EMPLOYEEADDRESS t ON e.EMPLOYEEID = t.EMPLOYEEID AND t.ADDRESS_TYPE = 0 inner join EMPLOYEEADDRESS p ON e.EMPLOYEEID = p.EMPLOYEEID AND p.ADDRESS_TYPE = 1";// where e.EMPLOYEEID=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSetOne;
            resultSetOne = preparedStatement.executeQuery();
            while (resultSetOne.next()) {
                EmployeePersonalDetails employee = new EmployeePersonalDetails();
                EmployeeAddressDetails permenantAddress = new EmployeeAddressDetails();
                EmployeeAddressDetails temporaryAddress = new EmployeeAddressDetails();
                employee.setFirstNameOfEmployee(resultSetOne.getString(1));
                employee.setMiddleNameOfEmployee(resultSetOne.getString(2));
                employee.setLastNameOfEmployee(resultSetOne.getString(3));
                employee.setEmployeeID(resultSetOne.getInt(4));
                employee.setEmployeeContactNumber(resultSetOne.getLong(5));
                employee.setEmployeeEmail(resultSetOne.getString(6));
                    permenantAddress.setHouseName(resultSetOne.getString(7));
                    permenantAddress.setStreetName(resultSetOne.getString(8));
                    permenantAddress.setCity(resultSetOne.getString(9));
                    permenantAddress.setState(resultSetOne.getString(10));
                    permenantAddress.setPincode(resultSetOne.getInt(11));
//                if (resultSetOne.next()) {
                    temporaryAddress.setHouseName(resultSetOne.getString(12));
                    temporaryAddress.setStreetName(resultSetOne.getString(13));
                    temporaryAddress.setCity(resultSetOne.getString(14));
                    temporaryAddress.setState(resultSetOne.getString(15));
                    temporaryAddress.setPincode(resultSetOne.getInt(16));

                employee.setPermanentAddress(permenantAddress);
                employee.setTemporaryAddress(temporaryAddress);
                employeeList.add(employee);

            }
            preparedStatement.close();
            connection.close();
            resultSetOne.close();


        } catch (ConnectionException exp) {
            throw new ConnectionException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(employeeList==null){
            throw new EmployeeException(resourceBundle.getString("Employee list not found"));
        }
        else{
            return employeeList;
        }

    }

    @Override
    public List<EmployeePersonalDetails> searchByPinCode(int pinCode) {
        List<EmployeePersonalDetails> employeeList = new ArrayList<>();
        ResultSet resultSetOne;
        try {
            DriverInitializer setConnection = new DriverInitializer();
            Connection connection = setConnection.makeConnection();
//            String query = "select e.FIRSTNAMEOFEMPLOYEE,e.MIDDLENAMEOFEMPLOYEE,e.LASTNAMEOFEMPLOYEE,e.EMPLOYEEID,e.EMPLOYEECONTACTNUMBER,e.EMPLOYEEEMAIL,t.HOUSENAME,t.STREETNAME,t.CITY,t.STATENAME,t.PINCODE from EMPLOYEEPERSONAL e join EMPLOYEEADDRESS t on e.EMPLOYEEID=t.EMPLOYEEID where t.ADDRESS_TYPE in (1,0)";
            String query = "select e.FIRSTNAMEOFEMPLOYEE,e.MIDDLENAMEOFEMPLOYEE,e.LASTNAMEOFEMPLOYEE,e.EMPLOYEEID,e.EMPLOYEECONTACTNUMBER,e.EMPLOYEEEMAIL, p.HOUSENAME,p.STREETNAME,p.CITY,p.STATENAME,p.PINCODE, t.HOUSENAME,t.STREETNAME,t.CITY,t.STATENAME,t.PINCODE from EMPLOYEEPERSONAL e inner join EMPLOYEEADDRESS p ON e.EMPLOYEEID = p.EMPLOYEEID AND p.ADDRESS_TYPE = 1 inner join EMPLOYEEADDRESS t ON e.EMPLOYEEID = t.EMPLOYEEID AND t.ADDRESS_TYPE = 1 where t.PINCODE=? or p.PINCODE=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pinCode);
            preparedStatement.setInt(2, pinCode);

            resultSetOne = preparedStatement.executeQuery();
            while (resultSetOne.next()) {
                EmployeePersonalDetails employee = new EmployeePersonalDetails();
                EmployeeAddressDetails permenantAddress = new EmployeeAddressDetails();
                EmployeeAddressDetails temporaryAddress = new EmployeeAddressDetails();
                employee.setFirstNameOfEmployee(resultSetOne.getString(1));
                employee.setMiddleNameOfEmployee(resultSetOne.getString(2));
                employee.setLastNameOfEmployee(resultSetOne.getString(3));
                employee.setEmployeeID(resultSetOne.getInt(4));
                employee.setEmployeeContactNumber(resultSetOne.getLong(5));
                employee.setEmployeeEmail(resultSetOne.getString(6));
                permenantAddress.setHouseName(resultSetOne.getString(7));
                permenantAddress.setStreetName(resultSetOne.getString(8));
                permenantAddress.setCity(resultSetOne.getString(9));
                permenantAddress.setState(resultSetOne.getString(10));
                permenantAddress.setPincode(resultSetOne.getInt(11));
//                if (resultSetOne.next()) {
                temporaryAddress.setHouseName(resultSetOne.getString(12));
                temporaryAddress.setStreetName(resultSetOne.getString(13));
                temporaryAddress.setCity(resultSetOne.getString(14));
                temporaryAddress.setState(resultSetOne.getString(15));
                temporaryAddress.setPincode(resultSetOne.getInt(16));
//                    temporaryAddress.setHouseName(resultSetOne.getString(7));
//                    temporaryAddress.setStreetName(resultSetOne.getString(8));
//                    temporaryAddress.setCity(resultSetOne.getString(9));
//                    temporaryAddress.setState(resultSetOne.getString(10));
//                    temporaryAddress.setPincode(resultSetOne.getInt(11));
//                }

                employee.setPermanentAddress(permenantAddress);
                employee.setTemporaryAddress(temporaryAddress);
                employeeList.add(employee);
            }
            preparedStatement.close();
            connection.close();
            resultSetOne.close();


        } catch (ConnectionException exp) {
            throw new ConnectionException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (employeeList.equals(null)) {
            throw new EmployeeException(resourceBundle.getString("Employee pincode not found"));
        } else {
            return employeeList;
        }
    }
//        List<EmployeePersonalDetails> employeeList = new ArrayList<>();
//        try {
//            DriverInitializer setConnection = new DriverInitializer();
//            Connection connection = setConnection.makeConnection();
//            ResultSet resultSet;
//            try {
//                String query="select e.FIRSTNAMEOFEMPLOYEE,e.EMPLOYEEEMAIL,e.EMPLOYEEID,a.PERMANENTHOUSENAME,a.PERMANENTSTREETNAME,a.PERMANENTCITY,a.PERMANENTSTATENAME,a.PERMANENTPINCODE, a.TEMPORARYHOUSENAME,a.TEMPORARYSTREETNAME,a.TEMPORARYCITY,a.TEMPORARYSTATENAME,a.TEMPORARYPINCODE from EMPLOYEEPERSONAL e join EMPLOYEEADDRESS a on e.EMPLOYEEID=a.EMPLOYEEID where a.PERMANENTPINCODE=? or a.TEMPORARYPINCODE=?";
//                PreparedStatement preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setInt(1, pin);
//                preparedStatement.setInt(2, pin);
//                EmployeePersonalDetails employee = new EmployeePersonalDetails();
//                EmployeeAddressDetails temporaryAddress = new EmployeeAddressDetails();
//                EmployeeAddressDetails permenantAddress = new EmployeeAddressDetails();
//                resultSet = preparedStatement.executeQuery();
//                while (resultSet.next()) {
//                    employee.setFirstNameOfEmployee(resultSet.getString(1));
//                    employee.setEmployeeEmail(resultSet.getString(2));
//                    employee.setEmployeeID(resultSet.getInt(3));
//                    permenantAddress.setHouseName(resultSet.getString(4));
//                    permenantAddress.setStreetName(resultSet.getString(5));
//                    permenantAddress.setCity(resultSet.getString(6));
//                    permenantAddress.setState(resultSet.getString(7));
//                    permenantAddress.setPincode(resultSet.getInt(8));
//                    if(resultSet.next()) {
//                        temporaryAddress.setHouseName(resultSet.getString(9));
//                        temporaryAddress.setStreetName(resultSet.getString(10));
//                        temporaryAddress.setCity(resultSet.getString(11));
//                        temporaryAddress.setState(resultSet.getString(12));
//                        temporaryAddress.setPincode(resultSet.getInt(13));
//                    }
//                    employee.setPermanentAddress(permenantAddress);
//                    employee.setTemporaryAddress(temporaryAddress);
//                    employeeList.add(employee);
//                }
//                preparedStatement.close();
//                connection.close();
//                resultSet.close();
//
//
//
//            } catch (SQLException exp) {
//                throw new SQLException();
//            }
//        }catch (ConnectionException | SQLException connectionExcp){
//            throw new ConnectionException();
//        }
//        return employeeList;
//    }
//    }
    @Override
    public boolean delete(int employeeID) {
        try{
            DriverInitializer setConnection = new DriverInitializer();
            Connection connection = setConnection.makeConnection();
            String query="DELETE FROM EMPLOYEEPERSONAL WHERE EMPLOYEEID=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,employeeID);
            int ResultSet=preparedStatement.executeUpdate();
            if(ResultSet!=0) {
                return true;
            }
        }catch (SQLException | ConnectionException exp){
            System.out.println(exp);
            logger.info(resourceBundle.getString("connection.failed"));;
        }
        return false;
    }


//        try
//
//    {
//        DriverInitializer setConnection = new DriverInitializer();
//        Connection connection = setConnection.makeConnection();
//        String query = "select * from EmployeePersonal";
//        PreparedStatement preparedStatement;
//        preparedStatement = connection.prepareStatement(query);
//        resultSet = preparedStatement.executeQuery();
//        List<Object> employeeList = new ArrayList<>();
//        EmployeePersonalDetails personalDetails = null;
//        while (resultSet.next()) {
//            personalDetails = new EmployeePersonalDetails();
//            personalDetails.setFirstNameOfEmployee(resultSet.getString(1));
//            personalDetails.setMiddleNameOfEmployee(resultSet.getString(2));
//            personalDetails.setLastNameOfEmployee(resultSet.getString(3));
//            personalDetails.setEmployeeID(resultSet.getInt(4));
//            personalDetails.setEmployeeContactNumber(resultSet.getLong(5));
//            personalDetails.setEmployeeEmail(resultSet.getString(6));
//            employeeList.add(personalDetails);
////                }
////                System.out.println(employeeList);
////                return employeeList;
////            } catch (SQLException e) {
////                throw new EmployeeException();
////            }
//            query = "select * from EMPLOYEEPERMANENTADDRESS where EmployeeID=?";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, personalDetails.getEmployeeID());
//            resultSet = preparedStatement.executeQuery();
//            EmployeeAddressDetails employeePermanent = null;
//            while (resultSet.next()) {
//                employeePermanent.setHouseNumber(resultSet.getInt(1));
//                employeePermanent.setStreetName(resultSet.getString(2));
//                employeePermanent.setCity(resultSet.getString(3));
//                employeePermanent.setState(resultSet.getString(4));
//                employeePermanent.setPincode(resultSet.getInt(5));
//                employeeList.add(personalDetails);
//            }
////        create table EmployeePermanentAddress(AddrressLane1 varchar(255) not null, AddrressLane2 varchar(255) not null, StreetName varchar(255) not null,City number varchar(255) not null, State varchar(255) not null, Pincode number not null);
//
//            System.out.println(employeeList);
//        } catch(SQLException e){
//        System.out.println(e);
//    }
//        try {
//            String query = "select * from EMPLOYEETEMPORARYADDRESS";
//            PreparedStatement preparedStatement;
//            preparedStatement=connection.prepareStatement(query);
//            resultSet=preparedStatement.executeQuery();
//            List<EmployeeAddressDetails> employeeList=new ArrayList<>();
//            EmployeeAddressDetails personalDetails = null;
//            while (resultSet.next()) {
//                personalDetails = new EmployeeAddressDetails();
//                personalDetails.setAddrressLane1(resultSet.getString(1));
//                personalDetails.setAddrressLane2(resultSet.getString(2));
//                personalDetails.setStreetName(resultSet.getString(3));
//                personalDetails.setCity(resultSet.getString(4));
//                personalDetails.setState(resultSet.getString(5));
//                personalDetails.setPincode(resultSet.getInt(6));
//                employeeList.add(personalDetails);
//            }
////        create table EmployeePermanentAddress(AddrressLane1 varchar(255) not null, AddrressLane2 varchar(255) not null, StreetName varchar(255) not null,City number varchar(255) not null, State varchar(255) not null, Pincode number not null);
//
//            System.out.println(employeeList);
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    return Collections.emptyList();
//    }
//    }

//        List<EmployeeAddressDetails> addressDetails = new ArrayList<>();
//
//        try {
//            String query = "select * from EmployeeAddress where pincode=? ";
//            PreparedStatement preparedStatement= connection.prepareStatement(query);
//            preparedStatement=connection.prepareStatement(query);
//            List<EmployeeAddressDetails> employeeList=new ArrayList<>();
//            preparedStatement.setInt(1,pincode);
//            resultSet = preparedStatement.executeQuery();
//            EmployeeAddressDetails personalDetails = null;
//            while (resultSet.next()) {
//                personalDetails = new EmployeeAddressDetails();
//                personalDetails.setAddrressLane1(resultSet.getString(1));
//                personalDetails.setAddrressLane2(resultSet.getString(2));
//                personalDetails.setStreetName(resultSet.getString(3));
//                personalDetails.setCity(resultSet.getString(4));
//                personalDetails.setState(resultSet.getString(5));
//                personalDetails.setPincode(resultSet.getInt(6));
//                employeeList.add(personalDetails);
//            }
//            System.out.println(employeeList);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    @Override
    public void writeIntoFile(EmployeePersonalDetails employeePersonalDetails) {

    }

    @Override
    public void writeIntoFileAdd(EmployeeAddressDetails employeeAddressDetails) {

    }

    @Override
    public ArrayList<Object> readFromFile() {
        return null;
    }

    @Override
    public ArrayList<Object> readFromFileAdd() {
        return null;
    }

}
