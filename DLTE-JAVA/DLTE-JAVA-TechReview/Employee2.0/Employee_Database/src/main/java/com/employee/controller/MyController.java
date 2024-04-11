package com.employee.controller;


import com.employee.entity.EmployeePersonalDetails;
import com.employee.exception.EmployeeException;
import com.employee.repository.MyInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class MyController {

    @Autowired
    MyInterface myInterface;
    Logger logger= LoggerFactory.getLogger(MyController.class);


    @PostMapping("/add")
    public String newEmployee(@RequestBody EmployeePersonalDetails personalDetails){
        try {
            String check = myInterface.input(personalDetails);
        }catch (EmployeeException | SQLException e){
            return e.getMessage();
        }
        return "Inserted";
    }
    @GetMapping("/all")
    public List<EmployeePersonalDetails> list() throws SQLException, IOException {
        System.out.println("listall");
        logger.info("list aal");
        return myInterface.display();
    }
    @GetMapping("/get/{pin}")
    public List<EmployeePersonalDetails> list(@PathVariable ("pin")Integer pin) throws SQLSyntaxErrorException {
        return myInterface.searchByPinCode(pin);
    }

    @GetMapping("/g")
    public String list2() {
        return "hello there";
    }
}
