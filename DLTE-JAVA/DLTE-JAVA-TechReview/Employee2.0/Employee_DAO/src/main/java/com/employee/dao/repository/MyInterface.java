package com.employee.dao.repository;

import com.employee.dao.entity.EmployeePersonalDetails;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface MyInterface {
    public String input(EmployeePersonalDetails employeePersonalDetails) throws SQLException;
    List<EmployeePersonalDetails> display() throws IOException, SQLException;
    List<EmployeePersonalDetails> searchByPinCode(int pincode) throws SQLSyntaxErrorException;
    boolean delete(int EmployeeId);
}