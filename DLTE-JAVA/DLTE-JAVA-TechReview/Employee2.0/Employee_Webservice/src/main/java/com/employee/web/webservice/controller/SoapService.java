package com.employee.web.webservice.controller;

import com.employee.dao.entity.EmployeeAddressDetails;
import com.employee.dao.entity.EmployeePersonalDetails;
import com.employee.dao.exception.EmployeeNotFoundException;
import com.employee.dao.repository.MyInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.employee.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;


@ComponentScan( "com.employee.dao")
@Endpoint
public class SoapService {
    Logger logger = LoggerFactory.getLogger(SoapService.class);

    private final String url = "http://employee.services";

    @Autowired
    private MyInterface myInterface;

    @PayloadRoot(namespace = url,localPart = "newEmployeeRequest")
    @ResponsePayload
    public NewEmployeeResponse createEmployee(@Valid @RequestPayload NewEmployeeRequest request) throws EmployeeNotFoundException {
        NewEmployeeResponse employeeResponse = new NewEmployeeResponse();

        ServiceStatus serviceStatus = new ServiceStatus();
        EmployeePersonal actualEmployee = request.getEmployeePersonal();
        try {
           EmployeePersonalDetails daoEmployee=new EmployeePersonalDetails();
            EmployeeAddressDetails daoTempAddress=new EmployeeAddressDetails();
            EmployeeAddressDetails daoPermAddress=new EmployeeAddressDetails();

            BeanUtils.copyProperties(actualEmployee,daoEmployee);
            BeanUtils.copyProperties(actualEmployee.getEmployeePermanentAddress(), daoPermAddress);
            BeanUtils.copyProperties(actualEmployee.getEmployeeTemporaryAddress(), daoTempAddress);

            daoEmployee.setFirstNameOfEmployee(actualEmployee.getFirstNameOfEmployee());
            daoEmployee.setMiddleNameOfEmployee(actualEmployee.getMiddleNameOfEmployee());
            daoEmployee.setLastNameOfEmployee(actualEmployee.getLastNameOfEmployee());
            daoEmployee.setEmployeeID(actualEmployee.getEmployeeID());
            daoEmployee.setEmployeeContactNumber(actualEmployee.getEmployeeContactNumber());
            daoEmployee.setEmployeeEmail(actualEmployee.getEmployeeEmail());
            daoEmployee.setPermanentAddress(daoPermAddress);
            daoEmployee.setTemporaryAddress(daoTempAddress);

            String check=myInterface.input(daoEmployee);
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            employeeResponse.setEmployee(actualEmployee);
            serviceStatus.setMessage("Input Success");
            logger.info("Insertion Success");

        } catch (EmployeeNotFoundException | SQLException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("Insertion Failure");
            logger.warn("Failed to insert employee data");
        }
        employeeResponse.setServiceStatus(serviceStatus);
        return employeeResponse;
    }


    @PayloadRoot(namespace = url, localPart = "findAllEmployeeRequest")
    @ResponsePayload
    public FindAllEmployeeResponse listAllEmployee(@RequestPayload FindAllEmployeeRequest findAllEmployeeRequest) {
        FindAllEmployeeResponse findAllEmployeeResponse = new FindAllEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<EmployeePersonal> employees = new ArrayList<>();
        try {
            List<EmployeePersonalDetails> daoEmployee = myInterface.display();
            System.out.println(daoEmployee.get(0).getTemporaryAddress().getHouseName());
            daoEmployee.forEach(each -> {
                EmployeePersonal currentEmployee = new EmployeePersonal();
                EmployeeAddress tempAddress = new EmployeeAddress();
                EmployeeAddress permAddress = new EmployeeAddress();
                BeanUtils.copyProperties(each, currentEmployee);

                BeanUtils.copyProperties(each.getTemporaryAddress(), tempAddress);
                BeanUtils.copyProperties(each.getPermanentAddress(), permAddress);
                currentEmployee.setFirstNameOfEmployee(each.getFirstNameOfEmployee());
                currentEmployee.setMiddleNameOfEmployee(each.getMiddleNameOfEmployee());
                currentEmployee.setLastNameOfEmployee(each.getLastNameOfEmployee());
                currentEmployee.setEmployeeID(each.getEmployeeID());
                currentEmployee.setEmployeeContactNumber(each.getEmployeeContactNumber());
                currentEmployee.setEmployeeEmail(each.getEmployeeEmail());
                System.out.println(tempAddress.getHouseName());


                currentEmployee.setEmployeeTemporaryAddress(tempAddress);
                System.out.println(currentEmployee.getEmployeeTemporaryAddress().getHouseName());

                employees.add(currentEmployee);
            });
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage("Employee details fetched");
            logger.info("Displaying the details");
        } catch (EmployeeNotFoundException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("Employee not found");
            logger.warn("Not found");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        findAllEmployeeResponse.setServiceStatus(serviceStatus);
        findAllEmployeeResponse.getEmployee().addAll(employees);
        return findAllEmployeeResponse;
    }


    @PayloadRoot(namespace = url,localPart = "getEmployeeByPinCodeRequest")
    @ResponsePayload
    public GetEmployeeByPinCodeResponse filterByPinCodeResponse(@RequestPayload GetEmployeeByPinCodeRequest filterAllEmployeeRequest) {
        GetEmployeeByPinCodeResponse getEmployeeByPinCodeResponse = new GetEmployeeByPinCodeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<EmployeePersonal> employees = new ArrayList<>();
        try {
            List<EmployeePersonalDetails> daoEmployees = myInterface.searchByPinCode(filterAllEmployeeRequest.getPincode());
                daoEmployees.forEach(each -> {
                EmployeePersonal currentEmployee = new EmployeePersonal();
                EmployeeAddress tempAddress = new EmployeeAddress();
                EmployeeAddress permAddress = new EmployeeAddress();
                BeanUtils.copyProperties(each, currentEmployee);
                BeanUtils.copyProperties(each.getPermanentAddress(), permAddress);
                BeanUtils.copyProperties(each.getTemporaryAddress(), tempAddress);
                currentEmployee.setFirstNameOfEmployee(each.getFirstNameOfEmployee());
                currentEmployee.setMiddleNameOfEmployee(each.getMiddleNameOfEmployee());
                currentEmployee.setLastNameOfEmployee(each.getLastNameOfEmployee());
                currentEmployee.setEmployeeID(each.getEmployeeID());
                currentEmployee.setEmployeeContactNumber(each.getEmployeeContactNumber());
                currentEmployee.setEmployeeEmail(each.getEmployeeEmail());

                employees.add(currentEmployee);
            });
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage("Employee details on pincode fetched");
            logger.info("Employee found");
        } catch (EmployeeNotFoundException | SQLSyntaxErrorException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("Employee details not found");
            logger.warn("Employee details for the pincode not found");
        }
        getEmployeeByPinCodeResponse.setServiceStatus(serviceStatus);
        getEmployeeByPinCodeResponse.getEmployee().addAll(employees);
        return getEmployeeByPinCodeResponse;
    }


}