package org.consoleapp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface EmplyeeInterface {
    ArrayList<EmployeePersonalDetails> employeeArray = new ArrayList<>();
    ArrayList<EmployeeAddressDetails> employeeArray2 = new ArrayList<>();
    void displayList() throws IOException, ClassNotFoundException;
    //Overloading in interface
    //void displayList(int hello);
    void addEmployeeDetails(EmployeePersonalDetails employeeInfo,EmployeeAddressDetails employeeAddressInfo) throws ClassNotFoundException, IOException;
}
