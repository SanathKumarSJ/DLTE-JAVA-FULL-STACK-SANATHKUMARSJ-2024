package org.consoleapp;

import org.employeefile.OperationOnEmployee;

import java.io.*;
import java.util.*;

public class EmployeeFile{
//        ArrayList<EmployeePersonalDetails> employeeArray = new ArrayList<>();
//    ArrayList<EmployeeAddressDetails> employeeArray2 = new ArrayList<>();
    static ArrayList<Object> employeeArray=new ArrayList<>();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application.properties");
        EmployeeFile employeeFile = new EmployeeFile();
        OperationOnEmployee operationOnEmployee=new OperationOnEmployee();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add new Employee Details");
            System.out.println("2. Check available List");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    EmployeePersonalDetails employeePersonalDetails=new EmployeePersonalDetails();
                    System.out.println("Enter the first name of employee");
                    String fName = scanner.nextLine();
                    System.out.println("Enter the middle name of employee");
                    String mName = scanner.nextLine();
                    System.out.println("Enter the last name of employee");
                    String lName = scanner.nextLine();

                    System.out.println("Please enter Employee ID");
                    Integer id = scanner.nextInt();

                    System.out.println("Please enter employee mobile number");
                    Long mobile = scanner.nextLong();
                    System.out.println("Please enter employee Email Address");
                    String mail = scanner.next();
//                    System.out.println(scanner.nextLine());
//                    System.out.println("Enter the Permanent address lane1");
//                    String permanent1 = scanner.nextLine();
//                    System.out.println("Enter the Permanent address lane2");
//                    String permanent2 = scanner.nextLine();
//                    System.out.println("Enter the Permanent address street name");
//                    String permanentStreet = scanner.nextLine();
//                    System.out.println("Enter the Permanent address city name");
//                    String permanentCity = scanner.nextLine();
//                    System.out.println("Enter the Permanent address state name");
//                    String permanentState = scanner.nextLine();
//                    System.out.println("Enter the Permanent address pin code");
//                    Integer permanentPin = scanner.nextInt();
//                    scanner.nextLine();
//                    System.out.println("Enter the Temporary address lane1");
//                    String temporary1 = scanner.nextLine();
//                    System.out.println("Enter the Temporary address lane2");
//                    String temporary2 = scanner.nextLine();
//                    System.out.println("Enter the Temporary address street name");
//                    String temporaryStreet = scanner.nextLine();
//                    System.out.println("Enter the Temporary address city name");
//                    String temporaryCity = scanner.nextLine();
//                    System.out.println("Enter the Temporary address state name");
//                    String temporaryState = scanner.nextLine();
//                    System.out.println("Enter the Temporary address pin code");
//                    Integer temporaryPin = scanner.nextInt();
//                    EmployeeAddressDetails employePermanent = new EmployeeAddressDetails(permanent1,permanent2,permanentStreet,permanentCity,permanentState,permanentPin);
//                    EmployeeAddressDetails employeeTemorary=new EmployeeAddressDetails(temporary1,temporary2,temporaryStreet,temporaryCity,temporaryState,temporaryPin);
                    employeeArray.add(new EmployeePersonalDetails(fName, mName, lName, id, mobile, mail));
                    operationOnEmployee.writeIntoFile(employeeArray);
                    System.out.println(employeeArray.size());
                    break;
                case 2:
                    System.out.println("Displaying the list");
                    //operationOnEmployee.writeIntoFile(employeeArray);
                    ArrayList<Objects> array;
                    array = operationOnEmployee.readFromFile();
                    System.out.println(array.size());
                    System.out.println(array);
                    break;
                default:
                    System.exit(0);
            }
        }
    }
}


