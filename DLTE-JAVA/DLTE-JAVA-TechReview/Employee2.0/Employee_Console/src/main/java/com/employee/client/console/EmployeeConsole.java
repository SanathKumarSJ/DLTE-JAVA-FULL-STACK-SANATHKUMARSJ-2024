package com.employee.client.console;

import com.employee.client.console.configuration.WebConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;
import services.employee.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EmployeeConsole {
    static int count = 0;
    static Logger logger = LoggerFactory.getLogger(EmployeeConsole.class);
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("console");

    public static void main(String[] args) throws IOException, SQLException {
        EmployeeConsole employeeConsole = new EmployeeConsole();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(WebConfiguration.class);
        context.refresh();

        EmployeePersonal personalDetails2;
        WebServiceTemplate webServiceTemplate = context.getBean(WebServiceTemplate.class);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(resourceBundle.getString("app.menu"));
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    do {
                        personalDetails2 = employeeConsole.inputPersonal(webServiceTemplate);
                        NewEmployeeRequest request = new NewEmployeeRequest();
                        request.setEmployeePersonal(personalDetails2);
                        //invocation
                        NewEmployeeResponse response = (NewEmployeeResponse) webServiceTemplate.marshalSendAndReceive(request);
                        ServiceStatus status = response.getServiceStatus();
                        System.out.println(status.getStatus());

                        System.out.println("Do you want to add one more record \nEnter --> yes to continue\n--> no to exit?");
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    break;
                }

                case 2: {
                    FindAllEmployeeRequest request = new FindAllEmployeeRequest();

                    FindAllEmployeeResponse response = (FindAllEmployeeResponse) webServiceTemplate.marshalSendAndReceive(request);

                    ServiceStatus status = response.getServiceStatus();
                    System.out.println("RECORDS FETCHED SUCCESSFULLY " + status.getStatus());
                    List<EmployeePersonal> employees = response.getEmployee();
                    for (EmployeePersonal employee : employees) {
                        System.out.println(displayEmployee(employee));
                    }
                    break;
                }


                case 3: {
                    System.out.println("Enter the Pin code");
                    int pinCode = scanner.nextInt();
                    GetEmployeeByPinCodeRequest request = new GetEmployeeByPinCodeRequest();
                    request.setPincode(pinCode);
                    GetEmployeeByPinCodeResponse response = (GetEmployeeByPinCodeResponse) webServiceTemplate.marshalSendAndReceive(request);
                    ServiceStatus status = response.getServiceStatus();
                    System.out.println("RECORDS FETCHED SUCCESSFULLY " + status.getStatus());
                    List<EmployeePersonal> employees = response.getEmployee();

                    for (EmployeePersonal employee : employees) {
                        System.out.println(displayEmployeeOnPin(employee));
                    }
                    break;
                }
            }
        }
    }

    public static String displayEmployee(EmployeePersonal employee) {
        //
        count = count + 1;
        return "Employee Details: " + count +
                "\nEmployee ID: " + employee.getEmployeeID() +
                "\nName: " + employee.getFirstNameOfEmployee() +
                "\nEmail: " + employee.getEmployeeEmail() +
                "\nPhone Number: " + employee.getEmployeeContactNumber() +
                "\n\nPermanent Address: " +
                "\nPermanent House Name: " + employee.getEmployeePermanentAddress().getHouseName() +
                "\nPermanent City: " + employee.getEmployeePermanentAddress().getCity() +
                "\nPermanent State: " + employee.getEmployeePermanentAddress().getState() +
                "\nPermanent Pin Code: " + employee.getEmployeePermanentAddress().getPincode() +
                "\n\nTemporary Address: " +
                "\nTemporary House name: " + employee.getEmployeeTemporaryAddress().getHouseName() +
                "\nTemporary City: " + employee.getEmployeeTemporaryAddress().getCity() +
                "\nTemporary State: " + employee.getEmployeeTemporaryAddress().getState() +
                "\nTemporary Pin Code: " + employee.getEmployeeTemporaryAddress().getPincode() +
                "\n -----------------------------------------------------";
    }

    public static String displayEmployeeOnPin(EmployeePersonal employee) {
        // Format employee details
        count = count + 1;
        return "Employee Details: " + count +
                "\nEmployee ID: " + employee.getEmployeeID() +
                "\nName: " + employee.getFirstNameOfEmployee() +
                "\nEmail: " + employee.getEmployeeEmail() +
                "\nPhone Number: " + employee.getEmployeeContactNumber() +
                "\n -----------------------------------------------------";

    }
    public EmployeePersonal inputPersonal(WebServiceTemplate webServiceTemplate) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        EmployeePersonal employeePersonalDetails1=new EmployeePersonal();
        System.out.println("Enter the first name of employee");
        employeePersonalDetails1.setFirstNameOfEmployee(scanner.nextLine());

        System.out.println("Enter the middle name of employee");
        employeePersonalDetails1.setMiddleNameOfEmployee(scanner.nextLine());

        System.out.println("Enter the last name of employee");
        employeePersonalDetails1.setLastNameOfEmployee(scanner.nextLine());

        System.out.println("Please enter 5 digit Employee ID");
        employeePersonalDetails1.setEmployeeID(scanner.nextInt());

        System.out.println("Please enter employee mobile number");
        employeePersonalDetails1.setEmployeeContactNumber(scanner.nextLong());

        System.out.println("Please enter employee Email Address");
        employeePersonalDetails1.setEmployeeEmail(scanner.next());

        System.out.println("Enter Permanent address details\n");

        EmployeeAddress employeeAddressDetails1=new EmployeeAddress();
        System.out.println("Enter the House Name");
        employeeAddressDetails1.setHouseName(scanner.next());
//        employeeAddressDetails2.setHouseName(employeeAddressDetails1.getHouseName());
        scanner.nextLine();
        System.out.println("Enter the address street name");
        employeeAddressDetails1.setStreetName(scanner.nextLine());
//        employeeAddressDetails2.setStreetName(employeeAddressDetails1.getStreetName());
        System.out.println("Enter the address city name");
        employeeAddressDetails1.setCity(scanner.nextLine());
//        employeeAddressDetails2.setCity(employeeAddressDetails1.getCity());
        System.out.println("Enter the address state name");
        employeeAddressDetails1.setState(scanner.nextLine());
//        employeeAddressDetails2.setState(employeeAddressDetails1.getState());
        System.out.println("Enter the address pin code");
        employeeAddressDetails1.setPincode(scanner.nextInt());

        System.out.println("Enter Temporary address details\n");
        EmployeeAddress employeeAddressDetails0=new EmployeeAddress();
        System.out.println("Enter the House Name");
        employeeAddressDetails0.setHouseName(scanner.next());
//        employeeAddressDetails2.setHouseName(employeeAddressDetails1.getHouseName());
        scanner.nextLine();
        System.out.println("Enter the address street name");
        employeeAddressDetails0.setStreetName(scanner.nextLine());
//        employeeAddressDetails2.setStreetName(employeeAddressDetails1.getStreetName());
        System.out.println("Enter the address city name");
        employeeAddressDetails0.setCity(scanner.nextLine());
//        employeeAddressDetails2.setCity(employeeAddressDetails1.getCity());
        System.out.println("Enter the address state name");
        employeeAddressDetails0.setState(scanner.nextLine());
//        employeeAddressDetails2.setState(employeeAddressDetails1.getState());
        System.out.println("Enter the address pin code");
        employeeAddressDetails0.setPincode(scanner.nextInt());

        employeePersonalDetails1.setEmployeePermanentAddress(employeeAddressDetails1);
        employeePersonalDetails1.setEmployeeTemporaryAddress(employeeAddressDetails0);
        return employeePersonalDetails1;
    }
}
