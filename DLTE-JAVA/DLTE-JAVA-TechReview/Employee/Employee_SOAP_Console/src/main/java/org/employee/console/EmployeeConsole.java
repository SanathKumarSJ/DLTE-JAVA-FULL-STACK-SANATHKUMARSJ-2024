package org.employee.console;

import org.backend.*;
import org.employee.EmployeeAddressDetails;
import org.employee.EmployeePersonalDetails;
import org.employee.EmployeeServiceService;
import org.employee.GroupOfEmployee;
import org.employee.console.validation.Validation;
import org.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.*;
import java.sql.SQLException;
import java.util.*;


public class EmployeeConsole {
    static int count=0;
    static Logger logger = LoggerFactory.getLogger(EmployeeConsole.class);
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Validation validation = new Validation();

    public static void main(String[] args) throws IOException, SQLException {
        MyInterface myInterface;
        myInterface = new EmployeeDB();
        EmployeeConsole employeeConsole = new EmployeeConsole();
        org.employee.console.entity.EmployeePersonalDetails employee = new org.employee.console.entity.EmployeePersonalDetails();
        org.employee.EmployeePersonalDetails personalDetails2;

        EmployeeServiceService service=new EmployeeServiceService();
        org.employee.EmployeeService employeeService=service.getEmployeeServicePort();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(resourceBundle.getString("app.menu"));
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    do {
                        personalDetails2 = employeeConsole.inputPersonal(employee);
                        System.out.println(personalDetails2);
                        try {
                            String check = employeeService.createEmployee(personalDetails2);
                            System.out.println(check);
                            System.out.println(resourceBundle.getString(check));
                        } catch (EmployeeException | ConnectionException | InvalidContactException | InvalidUserException exception) {
                            System.out.println(exception);
                            System.out.println("here you can enter next entry");
                            personalDetails2 = employeeConsole.inputPersonal(employee);
                            String check = employeeService.createEmployee(personalDetails2);
                        }
                        System.out.println("Do you want to add one more record \n Enter --> yes to continue\n --> no to exit?");
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    break;

                case 2:{
                    try {
                        GroupOfEmployee employees = employeeService.getAllEmployee();
                        List<org.employee.EmployeePersonalDetails> employeeList = employees.getEmployeePersonalDetails();
                        if (!employeeList.isEmpty()) {
                            for (org.employee.EmployeePersonalDetails emp : employeeList) {
                                System.out.println(formatEmployee(emp));
                                System.out.println();
                            }
                        } else {
                            logger.warn(resourceBundle.getString("employee.not.found"));
                        }
                    }
                    catch (SOAPFaultException e) {
                        System.out.println(e.getFault().getFaultString());
                        System.out.println(e.getFault().getFaultSubcodes());
                        logger.warn(e.getMessage());
                    }
                    break;
            }

                case 3: {
                    System.out.println("Enter the Pin code");
                    int pinCode = scanner.nextInt();
                    try {
                        GroupOfEmployee result = employeeService.searchByPincode(pinCode);
                        List<org.employee.EmployeePersonalDetails> employeeList = result.getEmployeePersonalDetails();
                        System.out.println(employeeList.toString());

                        if (!employeeList.isEmpty()) {
                            System.out.println(resourceBundle.getString("employee.pin") + pinCode + ":");
                            for (org.employee.EmployeePersonalDetails emp : employeeList) {
                                System.out.println(formatEmployee(emp));
                                System.out.println();
                            }
                        } else {
                            System.out.println(resourceBundle.getString("no.pin") + pinCode);
                        }
                    }catch (SOAPFaultException e) {
                        System.out.println(e.getFault().getFaultString()+e.getFault().getFaultCode());

                    }
                break;
                }

                case 4:
                    System.out.println("Enter Employee ID");
                    int id=scanner.nextInt();
                    try{
                        boolean status= myInterface.delete(id);

                        if(status==true){
                            logger.info("employee record deleted");
                        }
                        else {
                            logger.info("employee record not deleted");
                        }
                    }catch (NoDataException n){
                        System.out.println(n);
                    }
                default:
                    System.exit(0);
            }
        }
    }
    private static String formatEmployee(org.employee.EmployeePersonalDetails employee) {
        // Format employee details
        count=count+1;
        return "Employee Details: " +count+
                "\nEmployee ID: " + employee.getEmployeeID() +
                "\nName: " + employee.getFirstNameOfEmployee()+
                "\nEmail: " + employee.getEmployeeEmail() +
                "\nPhone Number: " + employee.getEmployeeContactNumber() +
                "\n\nPermanent Address: "  +
                "\nPermanent House Name: " + employee.getPermanentAddress().getHouseName() +
                "\nPermanent City: " + employee.getPermanentAddress().getCity()+
                "\nPermanent State: " + employee.getPermanentAddress().getState() +
                "\nPermanent Pin Code: " + employee.getPermanentAddress().getPincode() +
                "\n\nTemporary Address: " +
                "\nTemporary House name: " + employee.getTemporaryAddress().getHouseName() +
                "\nTemporary City: " + employee.getTemporaryAddress().getCity()+
                "\nTemporary State: " + employee.getTemporaryAddress().getState() +
                "\nTemporary Pin Code: " + employee.getTemporaryAddress().getPincode()+
                "\n -----------------------------------------------------";
    }

    public EmployeePersonalDetails inputPersonal(org.employee.console.entity.EmployeePersonalDetails employeePersonalDetails1) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        org.backend.EmployeePersonalDetails employeePersonalDetails2 = new org.backend.EmployeePersonalDetails();
        //console entity initialization
        org.employee.console.entity.EmployeeAddressDetails localPermenantAddress = new org.employee.console.entity.EmployeeAddressDetails();
        org.employee.console.entity.EmployeeAddressDetails localtemporaryAddress = new org.employee.console.entity.EmployeeAddressDetails();

        EmployeeConsole employeeConsole = new EmployeeConsole();
        System.out.println("Enter the first name of employee");
        employeePersonalDetails1.setFirstNameOfEmployee(scanner.nextLine());
//        employeePersonalDetails2.setFirstNameOfEmployee(employeePersonalDetails1.getFirstNameOfEmployee());
        while (!validation.isValidUsername(employeePersonalDetails1.getFirstNameOfEmployee())) {
//            logger.warn(resourceBundle.getString("app.username.invalid"));
            employeePersonalDetails1.setFirstNameOfEmployee(scanner.nextLine());
//            employeePersonalDetails2.setFirstNameOfEmployee(employeePersonalDetails1.getFirstNameOfEmployee());
        }
        System.out.println("Enter the middle name of employee");
        employeePersonalDetails1.setMiddleNameOfEmployee(scanner.nextLine());
//        employeePersonalDetails2.setMiddleNameOfEmployee(employeePersonalDetails1.getMiddleNameOfEmployee());
        System.out.println("Enter the last name of employee");
        employeePersonalDetails1.setLastNameOfEmployee(scanner.nextLine());
//        employeePersonalDetails2.setLastNameOfEmployee(employeePersonalDetails1.getLastNameOfEmployee());
        while (!validation.isValidLastUsername(employeePersonalDetails1.getLastNameOfEmployee())) {
            logger.warn(resourceBundle.getString("app.username.invalid"));
            employeePersonalDetails1.setLastNameOfEmployee(scanner.nextLine());
//            employeePersonalDetails2.setLastNameOfEmployee(employeePersonalDetails1.getLastNameOfEmployee());
        }
        System.out.println("Please enter 5 digit Employee ID");
        employeePersonalDetails1.setEmployeeID(scanner.nextInt());
        employeePersonalDetails2.setEmployeeID(employeePersonalDetails1.getEmployeeID());
        while (!validation.isValidID(employeePersonalDetails1.getEmployeeID())) { //validating password
            System.out.println(resourceBundle.getString("app.id.invalid"));
            //invalid
            System.out.println(resourceBundle.getString("app.id.format"));
            employeePersonalDetails1.setEmployeeID(scanner.nextInt());
//            employeePersonalDetails2.setEmployeeID(employeePersonalDetails1.getEmployeeID());
        }
        System.out.println("Please enter employee mobile number");
        employeePersonalDetails1.setEmployeeContactNumber(scanner.nextLong());
//        employeePersonalDetails2.setEmployeeContactNumber(employeePersonalDetails1.getEmployeeContactNumber());
        while (!validation.isValidContactNumber(employeePersonalDetails1.getEmployeeContactNumber())) { //validating contact
            // if invalid
            System.out.println(resourceBundle.getString("app.contact.invalid"));
            employeePersonalDetails1.setEmployeeContactNumber(scanner.nextLong());
//            employeePersonalDetails2.setEmployeeContactNumber(employeePersonalDetails1.getEmployeeContactNumber());
        }
        System.out.println("Please enter employee Email Address");
        employeePersonalDetails1.setEmployeeEmail(scanner.next());
//        employeePersonalDetails2.setEmployeeEmail(employeePersonalDetails1.getEmployeeEmail());
        while (!validation.isValidEmail(employeePersonalDetails1.getEmployeeEmail())) { //main-validation
            System.out.println(resourceBundle.getString("app.mail.invalid"));
            employeePersonalDetails1.setEmployeeEmail(scanner.next());
            //invalid
//            employeePersonalDetails2.setEmployeeEmail(employeePersonalDetails1.getEmployeeEmail());
        }
        employeeConsole.inputAdd(localPermenantAddress);
        EmployeeAddressDetails permenantAddress = new EmployeeAddressDetails();
        permenantAddress.setHouseName(localPermenantAddress.getHouseName());
        permenantAddress.setStreetName(localPermenantAddress.getStreetName());
        permenantAddress.setCity(localPermenantAddress.getCity());
        permenantAddress.setState(localPermenantAddress.getState());
        permenantAddress.setPincode(localPermenantAddress.getPincode());

        employeeConsole.inputAdd(localtemporaryAddress);
        EmployeeAddressDetails temporaryAddress = new EmployeeAddressDetails();
        temporaryAddress.setHouseName(localtemporaryAddress.getHouseName());
        temporaryAddress.setStreetName(localtemporaryAddress.getStreetName());
        temporaryAddress.setCity(localtemporaryAddress.getCity());
        temporaryAddress.setState(localtemporaryAddress.getState());
        temporaryAddress.setPincode(localtemporaryAddress.getPincode());

        logger.info("Data collected Successfully");

        EmployeePersonalDetails employeePersonalDetails = new EmployeePersonalDetails();
        employeePersonalDetails.setFirstNameOfEmployee(employeePersonalDetails1.getFirstNameOfEmployee());
        employeePersonalDetails.setMiddleNameOfEmployee(employeePersonalDetails1.getMiddleNameOfEmployee());
        employeePersonalDetails.setLastNameOfEmployee(employeePersonalDetails1.getLastNameOfEmployee());
        employeePersonalDetails.setEmployeeID(employeePersonalDetails1.getEmployeeID());
        employeePersonalDetails.setEmployeeContactNumber(employeePersonalDetails1.getEmployeeContactNumber());
        employeePersonalDetails.setEmployeeEmail(employeePersonalDetails1.getEmployeeEmail());
        employeePersonalDetails.setPermanentAddress(permenantAddress);
        employeePersonalDetails.setTemporaryAddress(temporaryAddress);

        System.out.println(employeePersonalDetails);
        scanner.close();
        return employeePersonalDetails;

    }

    public org.employee.console.entity.EmployeeAddressDetails inputAdd(org.employee.console.entity.EmployeeAddressDetails employeeAddressDetails1) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());
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
//        employeeAddressDetails2.setPincode(employeeAddressDetails1.getPincode());
        while (!validation.isValidPin(employeeAddressDetails1.getPincode())) { //validating contact
            // if invalid
            System.out.println(resourceBundle.getString("app.pin.invalid"));
            employeeAddressDetails1.setPincode(scanner.nextInt());
//            employeeAddressDetails2.setPincode(employeeAddressDetails1.getPincode());
        }
//        scanner.close();
        return employeeAddressDetails1;

    }


//    private static Employee transalte(org.webconsole.Details.Employee employee) {
//        Employee employee1=new Employee();
//        EmployeebasicDetails employeeDetails=new EmployeebasicDetails();
//        implementation.EmployeeAddress tempAddr=new implementation.EmployeeAddress();
//        implementation.EmployeeAddress perAddr=new implementation.EmployeeAddress();
//
//        employeeDetails.setEmployeeName(employee.getEmployeeBasicDetails().getEmployeeName());
//        employeeDetails.setEmployeeId(employee.getEmployeeBasicDetails().getEmployeeId());
//        employeeDetails.setEmailId(employee.getEmployeeBasicDetails().getEmailId());
//        employeeDetails.setPhoneNumber(employee.getEmployeeBasicDetails().getPhoneNumber());
//
//        perAddr.setAddress(employee.getEmployeePermanentAddress().getAddress());
//        perAddr.setHouseNumber(employee.getEmployeePermanentAddress().getHouseNumber());
//        perAddr.setCity(employee.getEmployeePermanentAddress().getCity());
//        perAddr.setState(employee.getEmployeePermanentAddress().getState());
//        perAddr.setPinCode(employee.getEmployeePermanentAddress().getPinCode());
//
//        tempAddr.setAddress(employee.getEmployeeTemporaryAddress().getAddress());
//        tempAddr.setHouseNumber(employee.getEmployeeTemporaryAddress().getHouseNumber());
//        tempAddr.setCity(employee.getEmployeeTemporaryAddress().getCity());
//        tempAddr.setState(employee.getEmployeeTemporaryAddress().getState());
//        tempAddr.setPinCode(employee.getEmployeeTemporaryAddress().getPinCode());
//
//        employee1.setEmployeebasicDetails(employeeDetails);
//        employee1.setEmployeePermanentAddress(perAddr);
//        employee1.setEmployeeTemporaryAddress(tempAddr);
//        return employee1;
//
//
//    }
    public List<org.employee.console.entity.EmployeePersonalDetails> translateList(List<org.employee.EmployeePersonalDetails> detailsList) throws IOException {
        List<org.employee.console.entity.EmployeePersonalDetails> employeeList = new ArrayList<>();
        int sizeEmp = detailsList.size();
        for (int index = 0; index < sizeEmp; index++) {
            employeeList.add(new org.employee.console.entity.EmployeePersonalDetails());
            employeeList.get(index).setFirstNameOfEmployee(detailsList.get(index).getFirstNameOfEmployee());
            employeeList.get(index).setMiddleNameOfEmployee(detailsList.get(index).getMiddleNameOfEmployee());
            employeeList.get(index).setLastNameOfEmployee(detailsList.get(index).getLastNameOfEmployee());
            employeeList.get(index).setEmployeeID(detailsList.get(index).getEmployeeID());
            employeeList.get(index).setEmployeeContactNumber(detailsList.get(index).getEmployeeContactNumber());
            employeeList.get(index).setEmployeeEmail(detailsList.get(index).getEmployeeEmail());

            org.employee.console.entity.EmployeeAddressDetails address = new org.employee.console.entity.EmployeeAddressDetails();
            address.setHouseName(detailsList.get(index).getPermanentAddress().getHouseName());
            address.setStreetName(detailsList.get(index).getPermanentAddress().getStreetName());
            address.setCity(detailsList.get(index).getPermanentAddress().getCity());
            address.setState(detailsList.get(index).getPermanentAddress().getState());
            address.setPincode(detailsList.get(index).getPermanentAddress().getPincode());

            employeeList.get(index).setPermanentAddress(address);

            org.employee.console.entity.EmployeeAddressDetails tempAddress = new org.employee.console.entity.EmployeeAddressDetails();
            tempAddress.setHouseName(detailsList.get(index).getTemporaryAddress().getHouseName());
            tempAddress.setStreetName(detailsList.get(index).getTemporaryAddress().getStreetName());
            tempAddress.setCity(detailsList.get(index).getTemporaryAddress().getCity());
            tempAddress.setState(detailsList.get(index).getTemporaryAddress().getState());
            tempAddress.setPincode(detailsList.get(index).getTemporaryAddress().getPincode());
            employeeList.get(index).setTemporaryAddress(tempAddress);
        }
        return employeeList;

    }

    public void displayOutput(List<org.employee.console.entity.EmployeePersonalDetails> employeeList) {
        int count = 1;
        for (org.employee.console.entity.EmployeePersonalDetails each : employeeList) {
            System.out.println("\nList of Employee " + count);
            count++;
            System.out.println("Name : " + (each.getFirstNameOfEmployee() + each.getMiddleNameOfEmployee() + each.getLastNameOfEmployee()) + "\nEmployeeId " + each.getEmployeeID() + "\nContact Number " + each.getEmployeeContactNumber() + "\nEmail " + each.getEmployeeEmail());
            System.out.println("Permanent Address\n" + each.getPermanentAddress().getHouseName() + "," + each.getPermanentAddress().getStreetName() + "," + each.getPermanentAddress().getCity() + "," + each.getPermanentAddress().getState() + " - " + each.getPermanentAddress().getPincode());
            System.out.println("Temporary Address\n" + each.getTemporaryAddress().getHouseName() + "," + each.getTemporaryAddress().getStreetName() + "," + each.getTemporaryAddress().getCity() + "," + each.getTemporaryAddress().getState() + " - " + each.getTemporaryAddress().getPincode());

        }
    }

    public void displayPincodeOutput(List<org.employee.console.entity.EmployeePersonalDetails> employeeList) {
        int count = 1;
        for (org.employee.console.entity.EmployeePersonalDetails each : employeeList) {
            System.out.println("\nList of Employee " + count);
            count++;
            System.out.println("Name : " + each.getFirstNameOfEmployee() + "\nEmployeeId " + each.getEmployeeID() + "\nEmail " + each.getEmployeeEmail());
            System.out.println("Permanent Address\n" + each.getPermanentAddress().getHouseName() + "," + each.getPermanentAddress().getStreetName() + "," + each.getPermanentAddress().getCity() + "," + each.getPermanentAddress().getState() + " - " + each.getPermanentAddress().getPincode());
            System.out.println("Temporary Address\n" + each.getTemporaryAddress().getHouseName() + "," + each.getTemporaryAddress().getStreetName() + "," + each.getTemporaryAddress().getCity() + "," + each.getTemporaryAddress().getState() + " - " + each.getTemporaryAddress().getPincode());

        }
    }
}

//    ALTER TABLE EMPLOYEEPERMANENTADDRESS
//        ADD FOREIGN KEY (EmployeeID)
//        REFERENCES EMPLOYEEPERSONAL(EmployeeID)
//        ON  DELETE CASCADE;