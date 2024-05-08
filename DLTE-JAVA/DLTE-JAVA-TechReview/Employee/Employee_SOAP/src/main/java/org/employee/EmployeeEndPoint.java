package org.employee;

import org.exception.ConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.Endpoint;

public class EmployeeEndPoint {
    static Logger logger = LoggerFactory.getLogger(EmployeeEndPoint.class);
    private static String url="http://localhost:2345/employee";
    public static void main(String[] args){
        EmployeeService employeeSoapService=new EmployeeService();

        Endpoint.publish(url,employeeSoapService);
        logger.info("Webservice Started");
        System.out.println("Webservice hosted @ "+url);
    }
}