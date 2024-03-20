package org.project1;
import org.db.EmployeeDatabaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeFile{
    static Logger logger = LoggerFactory.getLogger(EmployeeFile.class);
    Scanner scanner = new Scanner(System.in);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    static ArrayList<Object> employeeAllDetails = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        //Console
        EmployeeFile employeeConsole = new EmployeeFile();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(resourceBundle.getString("app.menu"));
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    try {
                        EmployeePersonalDetails employeePersonalDetails = new EmployeePersonalDetails();
                        employeeConsole.inputPersonal(employeePersonalDetails);
                        System.out.println("added");
//                        operationOnEmployee.writeIntoFile(employeeAllDetails);

                        logger.info(resourceBundle.getString("write.ok"));

                        System.out.println("Enter permanent details");

                        break;

                    } catch (EmployeeException | SQLException e) {
                        throw new EmployeeException();
                    }
                case 2:
                    employeeConsole.display();
                    break;

                case 3: {
                    employeeConsole.search();
                    break;
                }
                default:
                    System.exit(0);
            }
        }
    }


@Override
    public void inputPersonal(EmployeePersonalDetails employeePersonalDetails) throws SQLException {
        EmployeeFile employeeFile = new EmployeeFile();

        System.out.println("Enter the first name of employee");
        employeePersonalDetails.setFirstNameOfEmployee(scanner.nextLine());
        while (!isValidUsername(employeePersonalDetails.getFirstNameOfEmployee())) {
            logger.warn(resourceBundle.getString("app.username.invalid"));
            employeePersonalDetails.setFirstNameOfEmployee(scanner.nextLine());
        }
        System.out.println("Enter the middle name of employee");
        employeePersonalDetails.setMiddleNameOfEmployee(scanner.nextLine());
        System.out.println("Enter the last name of employee");
        employeePersonalDetails.setLastNameOfEmployee(scanner.nextLine());
        while (!isValidLastUsername(employeePersonalDetails.getLastNameOfEmployee())) {
            logger.warn(resourceBundle.getString("app.username.invalid"));
            employeePersonalDetails.setLastNameOfEmployee(scanner.nextLine());
        }
        System.out.println("Please enter 5 digit Employee ID");
        employeePersonalDetails.setEmployeeID(scanner.nextInt());
        while (!isValidID(employeePersonalDetails.getEmployeeID())) { //validating password
            System.out.println(resourceBundle.getString("app.id.invalid"));
            //invalid
            System.out.println(resourceBundle.getString("app.id.format"));
            employeePersonalDetails.setEmployeeID(scanner.nextInt());
        }
        System.out.println("Please enter employee mobile number");
        employeePersonalDetails.setEmployeeContactNumber(scanner.nextLong());
        while (!isValidContactNumber(employeePersonalDetails.getEmployeeContactNumber())) { //validating contact
            // if invalid
            System.out.println(resourceBundle.getString("app.contact.invalid"));
            employeePersonalDetails.setEmployeeContactNumber(scanner.nextLong());
        }
        System.out.println("Please enter employee Email Address");
        employeePersonalDetails.setEmployeeEmail(scanner.next());
        while (!isValidEmail(employeePersonalDetails.getEmployeeEmail())) { //main-validation
            System.out.println(resourceBundle.getString("app.mail.invalid"));
            //invalid
            employeePersonalDetails.setEmployeeEmail(scanner.next());
        }
//    EmployeeServices employeeServices=new EmployeeServices();
    EmployeeDatabaseRepository employeeDatabaseRepository=new EmployeeDatabaseRepository();
    System.out.println(employeePersonalDetails);
    employeeDatabaseRepository.input(employeePersonalDetails);
    }

@Override
    public EmployeeAddressDetails inputAdd() {
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
    while (!isValidPin(permanentPin)) { //validating contact
        // if invalid
        System.out.println(resourceBundle.getString("app.pin.invalid"));
        permanentPin = scanner.nextInt();
    }
    return new EmployeeAddressDetails(permanent1, permanent2, permanentStreet, permanentCity, permanentState, permanentPin);
}
@Override
    public void display() throws IOException {
//        OperationOnEmployee operationOnEmployee = new OperationOnEmployee();
//        //reading personal details
//        ArrayList<Object> arrayList;
//        arrayList = operationOnEmployee.readFromFile();
//        System.out.println(arrayList);
//        int size = arrayList.size();
//        HashMap<Integer, Object> temporaryAddress;
//        HashMap<Integer, Object> permanentAddress;
//
//        //reading temporary address
//        temporaryAddress = operationOnEmployee.readFromFileAddressTemporary();
//
//    //reading permanent address
//    permanentAddress = operationOnEmployee.readFromFileAddressPermanent();
//        System.out.println(temporaryAddress);
//        System.out.println(permanentAddress);
//        logger.info(resourceBundle.getString("display.ok"));
//    }
//
//    @Override
//    public void search() throws IOException {
//        System.out.println("Enter the pincode");
//        Integer inputPin=scanner.nextInt();
//        OperationOnEmployee operationOnEmployee = new OperationOnEmployee();
//        HashMap<Integer, Object> temporaryAddress;
//        HashMap<Integer, Object> permanentAddress;
//        temporaryAddress = operationOnEmployee.readFromFileAddressTemporary();
//        permanentAddress = operationOnEmployee.readFromFileAddressPermanent();
//        for(Map.Entry<Integer,Object> each:temporaryAddress.entrySet()){
//            int id =each.getKey();
//            Object temp=each.getValue();
//            EmployeeAddressDetails details=(EmployeeAddressDetails) temp;
//            if(details.getPincode().equals(inputPin)){
//                System.out.println("Pin Code Exist for Employee having ID "+id);
//            }
//        }
//        for(Map.Entry<Integer,Object> each:permanentAddress.entrySet()){
//            int id =each.getKey();
//            Object temp=each.getValue();
//            EmployeeAddressDetails details=(EmployeeAddressDetails) temp;
//            if(details.getPincode().equals(inputPin)){
//                System.out.println("Pin Code Exist for Employee having ID "+id);
//            }
//        }
    }

    @Override
    public void search() throws IOException {

    }

    @Override
    public void input(EmployeePersonalDetails employee) {

    }

    @Override
    public void inputAdd(EmployeeAddressDetails employeeAddressDetails) {

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

    public static Boolean isValidPin(Integer pin) {
        String contactString = Integer.toString(pin);
        String pinExpression = "^(?!0{6})\\d{6}$";
        Pattern pattern = Pattern.compile(pinExpression);
        Matcher matcher = pattern.matcher(contactString);
        return matcher.matches();
    }
}





































//    public void searchByPin() {
//        System.out.println("Enter the pincode");
//        String pincode=scanner.next();
//        ArrayList<EmployeePersonalDetails> employeePersonalDetails=re();
//        ArrayList<EmployeeAddressDetails> employeeAddresses=readAddressesFromFile();
//        boolean found =false;
//        int size=employeeAddresses.size();
//        for(int index=0;index<size;index++){
//            EmployeeAddress address=employeeAddresses.get(index);
//            if(address.getPermanentPincode().equals(pincode) || address.getTemporaryPincode().equals(pincode)){
//                EmployeeMain employeeMain=employeeMains.get(index);
//                found=true;
//                System.out.println("Details of employee with pincode "+ pincode);
//                System.out.println("Employee id "+employeeMain.getEmpId());
//                System.out.println("Employee First Name "+employeeMain.getFirstName());
//                System.out.println("Employee Middle Name "+employeeMain.getMiddleName());
//                System.out.println("Employee Last Name "+employeeMain.getLastName());
//                System.out.println("Employee Mobile Number "+employeeMain.getEmpMobileNumber());
//                System.out.println("Permanent Address "+address.getPermanentStreet()+","+address.getPermanentState()+","+address.getPermanentCountry());
//                System.out.println("Temporary Address "+address.getTemporaryStreet()+","+address.getTemporaryState()+","+address.getTemporaryCountry());
//            }
//for (int index = 0; index < size; index++) {
//        System.out.println("Employee " + (index + 1));
//        System.out.println(" ");
//        System.out.println(arrayList.get(index));
//        System.out.println("Permanent address " + permanentAddress.get(((EmployeePersonalDetails) arrayList.get(index)).getEmployeeID()));
//        System.out.println("Temporary address " + temporaryAddress.get(((EmployeePersonalDetails) arrayList.get(index)).getEmployeeID()));
//        System.out.println(" ");
//        }
//storing the permanent address in hashmap
//                        hashPermanantAddress.put(employeePersonalDetails.getEmployeeID(), employeeConsole.inputAdd());
//                        operationOnEmployee.writeIntoFileAddressPermanent(hashPermanantAddress);
//                        System.out.println("Enter temporary details");
//
//
//                        //storing the temporary address in hashmap
//                        hashTemporaryAddress.put(employeePersonalDetails.getEmployeeID(), employeeConsole.inputAdd());
//                        operationOnEmployee.writeIntoFileAddressTemporary(hashTemporaryAddress);