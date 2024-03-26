package org.backend;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Employee_File implements MyInterface{
        static File myfile= new File("employee.doc");
        static File AddresssFile= new File("EmployeeAddress");
//        static File temporaryFile= new File("temporaryAddress");

        public Employee_File() throws IOException {
            System.out.println("inside constructor");
            if (myfile.exists()){
                System.out.println("File present");
            }
            else
                System.out.println("file absent");
        }

    @Override
    public String input(EmployeePersonalDetails employeePersonalDetails) throws SQLException {
        return null;
    }

    public void writeIntoFile(EmployeePersonalDetails employee){
            readFromFile();
            System.out.println("Inside write");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(myfile);
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(employee);
                System.out.println("file written");
                objectOutputStream.close();
                fileOutputStream.close();
            }
            catch (IOException expection){
                System.out.println(expection);
            }
        }

    public void writeIntoFileAdd(EmployeeAddressDetails employeeAddressDetails){
        readFromFile();
        System.out.println("Inside write");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(AddresssFile);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employeeAddressDetails);
            System.out.println("file written");
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException expection){
            System.out.println(expection);
        }
    }
        public ArrayList<Object> readFromFile(){
            try{
                //Employee employee=new Employee();
                System.out.println("inside read");
                ArrayList<Object> employeeArray=new ArrayList<>();
                FileInputStream fileInputStream=new FileInputStream(myfile);
                ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
                employeeArray=((ArrayList<Object>) objectInputStream.readObject());
                objectInputStream.close();
                fileInputStream.close();
                return employeeArray;
            }
            catch (IOException | ClassNotFoundException  ioException){
                System.out.println(ioException);
            }
            return null;
        }
    public ArrayList<Object> readFromFileAdd(){
        try{
            //Employee employee=new Employee();
            System.out.println("inside read");
            ArrayList<Object> employeeArray=new ArrayList<>();
            FileInputStream fileInputStream=new FileInputStream(AddresssFile);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            employeeArray=((ArrayList<Object>) objectInputStream.readObject());
            objectInputStream.close();
            fileInputStream.close();
            return employeeArray;
        }
        catch (IOException | ClassNotFoundException  ioException){
            System.out.println(ioException);
        }
        return null;
    }

    @Override
    public List<EmployeePersonalDetails> display() throws IOException {
        return null;
    }

    @Override
    public List<EmployeePersonalDetails> searchByPinCode(int pincode) {
        return null;
    }

    @Override
    public boolean delete(int EmployeeId) {
        return false;
    }


}
