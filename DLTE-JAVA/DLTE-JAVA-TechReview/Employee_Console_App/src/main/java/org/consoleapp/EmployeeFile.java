package org.consoleapp;

import org.employeefile.OperationOnEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeFile{
    static ArrayList<Object> employeeArray=new ArrayList<>();
    static Logger logger= LoggerFactory.getLogger(EmployeeFile.class);
    Scanner scanner = new Scanner(System.in);
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        EmployeeFile employeeFile = new EmployeeFile();
        OperationOnEmployee operationOnEmployee=new OperationOnEmployee();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(resourceBundle.getString("app.menu"));
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    try {
                        EmployeePersonalDetails personalDetails = employeeFile.input();
                        employeeArray.add(personalDetails);
                        EmployeeAddressDetails employeeAddressDetails = employeeFile.inputAdd();
                        employeeArray.add(employeeAddressDetails);
                        operationOnEmployee.writeIntoFile(employeeArray);
                        logger.info(resourceBundle.getString("write.ok"));
                        System.out.println(employeeArray.size());
                    }catch (EmployeeException e){
                        throw new EmployeeException();
                    }
                    break;
                case 2:
                    System.out.println("Displaying the list");
                    //operationOnEmployee.writeIntoFile(employeeArray);
                    try {
                        ArrayList<Objects> array;
                        array = operationOnEmployee.readFromFile();
                        logger.info(resourceBundle.getString("display.ok"));
                        System.out.println(array.size());
                        System.out.println(array);
                    }catch (EmployeeException e){
                        throw new EmployeeException();
                    }
                    break;
                default:
                    System.exit(0);
            }
        }
    }

    public EmployeePersonalDetails input() {
        EmployeePersonalDetails employeePersonalDetails = new EmployeePersonalDetails();
        System.out.println("Enter the first name of employee");
        String fName = scanner.nextLine();
        while (!isValidUsername(fName)) {
            logger.warn(resourceBundle.getString("app.username.invalid"));
            fName = scanner.nextLine();
        }
        System.out.println("Enter the middle name of employee");
        String mName = scanner.nextLine();
        System.out.println("Enter the last name of employee");
        String lName = scanner.nextLine();
        while (!isValidLastUsername(lName)) {
            logger.warn(resourceBundle.getString("app.username.invalid"));
            lName = scanner.nextLine();
        }
        System.out.println("Please enter 5 digit Employee ID");
        Integer id = scanner.nextInt();
        while (!isValidID(id)) { //validating password
            System.out.println(resourceBundle.getString("app.id.invalid"));
            //invalid
            System.out.println(resourceBundle.getString("app.id.format"));
            id = scanner.nextInt();
        }
            System.out.println("Please enter employee mobile number");
            Long mobile = scanner.nextLong();
            while (!isValidContactNumber(mobile)) { //validating contact
                // if invalid
                System.out.println(resourceBundle.getString("app.contact.invalid"));
                mobile=scanner.nextLong();
            }
            System.out.println("Please enter employee Email Address");
            String mail = scanner.next();
            while (!isValidEmail(mail)) { //main-validation
                System.out.println(resourceBundle.getString("app.mail.invalid"));
                //invalid
                mail=scanner.next();
            }
            return (new EmployeePersonalDetails(fName, mName, lName, id, mobile, mail));

        }

    public EmployeeAddressDetails inputAdd(){
        System.out.println(scanner.nextLine());
        System.out.println("Enter the Permanent address lane1");
        String permanent1 = scanner.nextLine();
        System.out.println("Enter the Permanent address lane2");
        String permanent2 = scanner.nextLine();
        System.out.println("Enter the Permanent address street name");
        String permanentStreet = scanner.nextLine();
        System.out.println("Enter the Permanent address city name");
        String permanentCity = scanner.nextLine();
        System.out.println("Enter the Permanent address state name");
        String permanentState = scanner.nextLine();
        System.out.println("Enter the Permanent address pin code");
        Integer permanentPin = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the Temporary address lane1");
        String temporary1 = scanner.nextLine();
        System.out.println("Enter the Temporary address lane2");
        String temporary2 = scanner.nextLine();
        System.out.println("Enter the Temporary address street name");
        String temporaryStreet = scanner.nextLine();
        System.out.println("Enter the Temporary address city name");
        String temporaryCity = scanner.nextLine();
        System.out.println("Enter the Temporary address state name");
        String temporaryState = scanner.nextLine();
        System.out.println("Enter the Temporary address pin code");
        Integer temporaryPin = scanner.nextInt();
        return new EmployeeAddressDetails(permanent1,permanent2,permanentStreet,permanentCity,permanentState,permanentPin,temporary1,temporary2,temporaryStreet,temporaryCity,temporaryState,temporaryPin);
    }

    //Validation checking - Regex
    public static Boolean isValidUsername(String username) {
        String usernameExpression = "^[A-Za-z]\\w{5,29}$";
        Pattern pattern = Pattern.compile(usernameExpression);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    public static Boolean isValidLastUsername(String username) {
        String usernameExpression = "^[A-Za-z]\\w{1,29}$";
        Pattern pattern = Pattern.compile(usernameExpression);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    public static Boolean isValidEmail(String borrowerEmail) {
        String emailExpression = "^[A-Za-z0-9+_.-]+@[a-zA-Z]{3,}+\\.[a-z]{2,}";
        Pattern pattern = Pattern.compile(emailExpression);
        Matcher matcher = pattern.matcher(borrowerEmail);
        return matcher.matches();
    }
    public static Boolean isValidID(Integer idnumber) {
        String contactString = Integer.toString(idnumber);
        String mobileExpression = "^(?!0{5})\\d{5}$";
        Pattern pattern = Pattern.compile(mobileExpression);
        Matcher matcher = pattern.matcher(contactString);
        return matcher.matches();
    }
    public static Boolean isValidContactNumber(Long contactNumber) {
        String contactString = Long.toString(contactNumber);
        String mobileExpression = "^(?!0{10})\\d{10}$";
        Pattern pattern = Pattern.compile(mobileExpression);
        Matcher matcher = pattern.matcher(contactString);
        return matcher.matches();
    }
    public static Boolean isValidPassword(String password) {
        String passwordExpression = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(passwordExpression);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}