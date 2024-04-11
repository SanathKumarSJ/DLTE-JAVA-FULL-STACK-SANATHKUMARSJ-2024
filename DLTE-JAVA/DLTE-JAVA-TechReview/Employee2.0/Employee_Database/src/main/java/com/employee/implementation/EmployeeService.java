package com.employee.implementation;

import com.employee.entity.EmployeeAddressDetails;
import com.employee.entity.EmployeePersonalDetails;
import com.employee.exception.EmployeeException;
import com.employee.repository.MyInterface;
import com.employee.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class EmployeeService implements MyInterface {
    @Autowired
    JdbcTemplate jdbcTemplate ;
    private Validation validation = new Validation();
    static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    @Override
    public String input(EmployeePersonalDetails employeePersonalDetails) throws SQLException {
        String val=validation.validateEmployee(employeePersonalDetails);
        if(val!=null){
            return val;
        }
        int ack = jdbcTemplate.update("insert into EMPLOYEEPERSONAL values(?,?,?,?,?,?)",
                new Object[]{
                        employeePersonalDetails.getFirstNameOfEmployee(),
                        employeePersonalDetails.getMiddleNameOfEmployee(),
                        employeePersonalDetails.getLastNameOfEmployee(),
                        employeePersonalDetails.getEmployeeID(),
                        employeePersonalDetails.getEmployeeContactNumber(),
                        employeePersonalDetails.getEmployeeEmail()
                });
        if(ack==0)
            throw new EmployeeException(resourceBundle.getString(" Insertion failure"));

        int ack1 = jdbcTemplate.update("insert into EMPLOYEEADDRESS values(?,?,?,?,?,?,ADDID_SEQ.nextval,0)",
                new Object[]{
                        employeePersonalDetails.getPermanentAddress().getHouseName(),
                        employeePersonalDetails.getPermanentAddress().getStreetName(),
                        employeePersonalDetails.getPermanentAddress().getCity(),
                        employeePersonalDetails.getPermanentAddress().getState(),
                        employeePersonalDetails.getPermanentAddress().getPincode(),
                        employeePersonalDetails.getEmployeeID()
                });
        if(ack1==0)
            throw new EmployeeException(resourceBundle.getString(" Insertion failure Permanent Address"));

        int ack2 = jdbcTemplate.update("insert into EMPLOYEEADDRESS values(?,?,?,?,?,?,ADDID_SEQ.nextval,1)",
                new Object[]{
                        employeePersonalDetails.getTemporaryAddress().getHouseName(),
                        employeePersonalDetails.getTemporaryAddress().getStreetName(),
                        employeePersonalDetails.getTemporaryAddress().getCity(),
                        employeePersonalDetails.getTemporaryAddress().getState(),
                        employeePersonalDetails.getTemporaryAddress().getPincode(),
                        employeePersonalDetails.getEmployeeID()
                });
        if(ack2==0)
            throw new EmployeeException(resourceBundle.getString(" Insertion failure Temporary Address"));

        return "Insertion Success" ;
    }

    public List<EmployeePersonalDetails> display() throws SQLException {
        logger.info("Inside display");
        List<EmployeePersonalDetails> employeeList = new ArrayList<>();
        try{
            employeeList=jdbcTemplate.query("select * FROM EMPLOYEEPERSONAL",new BeanPropertyRowMapper<>());
        }
        catch (DataAccessException sqlException){
            throw new SQLSyntaxErrorException();
        }
        if(employeeList.size()==0){
            System.out.println("no data");
            throw new EmployeeException("No Employee found");
        }
        return employeeList;
    }


    @Override
    public List<EmployeePersonalDetails> searchByPinCode(int pinCode) throws SQLSyntaxErrorException {
        List<EmployeePersonalDetails> employeeList = new ArrayList<>();
//            String query = "select e.FIRSTNAMEOFEMPLOYEE,e.MIDDLENAMEOFEMPLOYEE,e.LASTNAMEOFEMPLOYEE,e.EMPLOYEEID,e.EMPLOYEECONTACTNUMBER,e.EMPLOYEEEMAIL,t.HOUSENAME,t.STREETNAME,t.CITY,t.STATENAME,t.PINCODE from EMPLOYEEPERSONAL e join EMPLOYEEADDRESS t on e.EMPLOYEEID=t.EMPLOYEEID where t.ADDRESS_TYPE in (1,0)";
        try{
            employeeList=jdbcTemplate.query("select e.FIRSTNAMEOFEMPLOYEE,e.MIDDLENAMEOFEMPLOYEE,e.LASTNAMEOFEMPLOYEE,e.EMPLOYEEID,e.EMPLOYEECONTACTNUMBER,e.EMPLOYEEEMAIL, p.HOUSENAME,p.STREETNAME,p.CITY,p.STATENAME,p.PINCODE, t.HOUSENAME,t.STREETNAME,t.CITY,t.STATENAME,t.PINCODE from EMPLOYEEPERSONAL e inner join EMPLOYEEADDRESS p ON e.EMPLOYEEID = p.EMPLOYEEID AND p.ADDRESS_TYPE = 1 inner join EMPLOYEEADDRESS t ON e.EMPLOYEEID = t.EMPLOYEEID AND t.ADDRESS_TYPE = 1 where t.PINCODE=? or p.PINCODE=?"
                    ,new Object[]{pinCode,pinCode},
                    new BeanPropertyRowMapper<>());
        }
        catch (DataAccessException sqlException){
            throw new SQLSyntaxErrorException();
        }
        if(employeeList.size()==0){
            throw new EmployeeException("No Employee found with the provided pin");
        }
        return employeeList;
    }

    public class EmployeeDetailsRowMapper implements RowMapper<EmployeePersonalDetails> {

        @Override
        public EmployeePersonalDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeePersonalDetails employeeDetails = new EmployeePersonalDetails();
            employeeDetails.setFirstNameOfEmployee(rs.getString("FIRSTNAMEOFEMPLOYEE"));
            employeeDetails.setMiddleNameOfEmployee(rs.getString("MIDDLENAMEOFEMPLOYEE"));
            employeeDetails.setLastNameOfEmployee(rs.getString("LASTNAMEOFEMPLOYEE"));
            employeeDetails.setEmployeeID(rs.getInt("EMPLOYEEID"));
            employeeDetails.setEmployeeContactNumber(rs.getLong("EMPLOYEECONTACTNUMBER"));
            employeeDetails.setEmployeeEmail(rs.getString("EMPLOYEEEMAIL"));
            // Mapping for primary address
            EmployeeAddressDetails primaryAddress = new EmployeeAddressDetails();
            primaryAddress.setHouseName(rs.getString("p.HOUSENAME"));
            primaryAddress.setStreetName(rs.getString("p.STREETNAME"));
            primaryAddress.setCity(rs.getString("p.CITY"));
            primaryAddress.setState(rs.getString("p.STATENAME"));
            primaryAddress.setPincode(rs.getInt("p.PINCODE"));
            employeeDetails.setPermanentAddress(primaryAddress);
            // Mapping for temporary address
            EmployeeAddressDetails temporaryAddress = new EmployeeAddressDetails();
            temporaryAddress.setHouseName(rs.getString("t.HOUSENAME"));
            temporaryAddress.setStreetName(rs.getString("t.STREETNAME"));
            temporaryAddress.setCity(rs.getString("t.CITY"));
            temporaryAddress.setState(rs.getString("t.STATENAME"));
            temporaryAddress.setPincode(rs.getInt("t.PINCODE"));
            employeeDetails.setTemporaryAddress(temporaryAddress);
            return employeeDetails;
        }
    }

    @Override
    public boolean delete(int EmployeeId) {
        return false;
    }


//    @Override
//    public boolean delete(int employeeID) {
//        try{
//            DriverInitializer setConnection = new DriverInitializer();
//            Connection connection = setConnection.makeConnection();
//            String query="DELETE FROM EMPLOYEEPERSONAL WHERE EMPLOYEEID=?";
//            PreparedStatement preparedStatement=connection.prepareStatement(query);
//            preparedStatement.setInt(1,employeeID);
//            int ResultSet=preparedStatement.executeUpdate();
//            if(ResultSet!=0) {
//                return true;
//            }
//        }catch (SQLException | ConnectionException exp){
//            System.out.println(exp);
//            logger.info(resourceBundle.getString("connection.failed"));;
//        }
//        return false;
//    }


}
