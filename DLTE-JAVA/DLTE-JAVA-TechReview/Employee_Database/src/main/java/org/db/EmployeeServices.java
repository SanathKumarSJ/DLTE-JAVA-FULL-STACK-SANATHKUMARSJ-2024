//package org.db;
//
//import org.example.EmployeePersonalDetails;
//import org.example.EmplyeeInterface;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class EmployeeServices {
////    DatabaseTarget databaseTarget = new DatabaseTarget();
//    EmployeeDatabaseRepository employeeDatabaseRepository=new EmployeeDatabaseRepository();
//    EmplyeeInterface employeeInterface;
//    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
//
//    //connection establishment
//    public EmployeeServices(DatabaseTarget databaseTarget) {
//        employeeInterface = databaseTarget.getEmployeeInterface();
//    }
//public EmployeeServices(){}
//    public void input(EmployeePersonalDetails employeePersonalDetails) {
//        System.out.println("db add");
//        System.out.println(employeePersonalDetails);
//        employeeInterface.input(employeePersonalDetails);
//    }
//
//        public void callDisplay() {
//            employeeDatabaseRepository.display();
//        }
//
//    }