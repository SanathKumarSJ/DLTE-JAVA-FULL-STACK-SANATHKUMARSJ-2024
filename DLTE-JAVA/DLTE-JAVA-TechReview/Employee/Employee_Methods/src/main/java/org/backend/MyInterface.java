package org.backend;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MyInterface {
    public String input(EmployeePersonalDetails employeePersonalDetails) throws SQLException;
    public void writeIntoFile(EmployeePersonalDetails employeePersonalDetails);
    public void writeIntoFileAdd(EmployeeAddressDetails employeeAddressDetails);
    public ArrayList<Object> readFromFile();
    public ArrayList<Object> readFromFileAdd();
    List<EmployeePersonalDetails> display() throws IOException, SQLException;
    List<EmployeePersonalDetails> searchByPinCode(int pincode);
    boolean delete(int EmployeeId);
}