//package com.employee.dao.controller;
//
//
//import com.employee.dao.entity.EmployeePersonalDetails;
//import com.employee.dao.exception.EmployeeException;
//import com.employee.dao.exception.EmployeeNotFoundException;
//import com.employee.dao.repository.MyInterface;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.sql.SQLSyntaxErrorException;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@ComponentScan("com.employee")
//@RequestMapping("/employee")
//public class Controller {
//
//    @Autowired
//    MyInterface myInterface;
//    Logger logger= LoggerFactory.getLogger(Controller.class);
//
//
//    @PostMapping("/add")
//    public String newEmployee(@RequestBody EmployeePersonalDetails personalDetails){
//        try {
//            String check = myInterface.input(personalDetails);
//        }catch (EmployeeException | SQLException e){
//            return e.getMessage();
//        }
//        return "Inserted";
//    }
//    @GetMapping("/all")
//    public List<EmployeePersonalDetails> list() throws SQLException, IOException {
//        try {
//            System.out.println("listall");
//            logger.info("list aal");
//            return myInterface.display();
//        }catch (EmployeeNotFoundException e){
//            return (List<EmployeePersonalDetails>) e;
//        }
//    }
//    @GetMapping("/get/{pin}")
//    public List<EmployeePersonalDetails> list(@PathVariable ("pin")Integer pin) throws SQLSyntaxErrorException {
//        List<EmployeePersonalDetails> employeePersonalDetails=new ArrayList<>();
//        try {
//            employeePersonalDetails= myInterface.searchByPinCode(pin);
//        }catch (EmployeeNotFoundException e){
//            System.out.println(e.toString());
//            return (List<EmployeePersonalDetails>) e;
//        }
//        return employeePersonalDetails;
//    }
//
//    @GetMapping("/g")
//    public String list2() {
//        return "hello there";
//    }
//}
