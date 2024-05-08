package org.employee;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import org.backend.EmployeeDB;
import org.backend.EmployeePersonalDetails;
import org.backend.MyInterface;
import org.exception.ConnectionException;
import org.exception.EmployeeException;
import org.exception.NoDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.ValidationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EmployeeService {
    private MyInterface myInterface;

    static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    public EmployeeService() {
        myInterface = new EmployeeDB();

    }

    private String exceptionHandler(Exception e) {
        if (e.getClass().equals(SQLException.class)) {
            return "SQLException";
        } else if (e.getClass().equals(EmployeeException.class)) {
            return "EmployeeNotFoundException";
        } else {
            return "Unknown Exception";
        }
    }
    @WebMethod
    public String createEmployee(@WebParam(name = "Employee") EmployeePersonalDetails employee) {
        String check = "";
        try {
            check = myInterface.input(employee);
            logger.info("Employee details passed to backend");
        } catch (EmployeeException | SQLException e) {
            e.printStackTrace();
            logger.warn("Backend transfer failure");

        }
        return check;
    }

    @WebMethod
    @WebResult(name = "findAll")
    public GroupOfEmployee getAllEmployee() {
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            List<EmployeePersonalDetails> employees = myInterface.display();
//                logger.info("Details fetched");
            groupOfEmployee.setEmployeePersonalDetails(employees);
        }  catch (Exception e) {
            // logger.error(e.getMessage() + e.getClass().getName());
            try {
                // Create a SOAPFault instance
                SOAPFault soapFault = SOAPFactory.newInstance().createFault();
                // Set fault code and message
                soapFault.setFaultCode(String.valueOf(500));
                soapFault.setFaultString("HTTP STATUS CODE:");
                // Throw SOAPFaultException with the SOAPFault
                throw new SOAPFaultException(soapFault);
            } catch (SOAPException ex) {
                logger.error(ex.getMessage());
            }
        }
        return groupOfEmployee;
    }

    @WebMethod
    @WebResult(name = "ListPincode")
    public GroupOfEmployee searchByPincode(@WebParam(name = "pincode")Integer pincode) {
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            List<EmployeePersonalDetails> employees = myInterface.searchByPinCode(pincode);
            groupOfEmployee.setEmployeePersonalDetails(employees);
        }catch (Exception e) {
            // logger.error(e.getMessage() + e.getClass().getName());
            try {
                // Create a SOAPFault instance
                SOAPFault soapFault = SOAPFactory.newInstance().createFault();
                // Set fault code and message
                soapFault.setFaultCode(String.valueOf(500));
                soapFault.setFaultString("HTTP STATUS CODE:");
                // Throw SOAPFaultException with the SOAPFault
                throw new SOAPFaultException(soapFault);
            } catch (SOAPException ex) {
                logger.error(ex.getMessage());
            }
        }

        return groupOfEmployee;
    }


}

//
//
//@WebService
//@SOAPBinding(style = SOAPBinding.Style.RPC)
//public class EmployeeService {
//    private MyInterface myInterface;
//
//    static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
//    public EmployeeService() {
//        myInterface = new EmployeeDB();
//
//    }
//
//    private String exceptionHandler(Exception e) {
//        if (e.getClass().equals(SQLException.class)) {
//            return "SQLException";
//        } else if (e.getClass().equals(EmployeeException.class)) {
//            return "EmployeeNotFoundException";
//        } else {
//            return "Unknown Exception";
//        }
//    }
//        @WebMethod
//        public String createEmployee(@WebParam(name = "Employee") EmployeePersonalDetails employee) {
//            String check = "";
//            try {
//                check = myInterface.input(employee);
//                logger.info("Employee details passed to backend");
//            } catch (EmployeeException | SQLException e) {
//                e.printStackTrace();
//                logger.warn("Backend transfer failure");
//
//            }
//            return check;
//        }
//
//        @WebMethod
//        @WebResult(name = "findAll")
//        public GroupOfEmployee getAllEmployee() {
//            GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
//            try {
//                List<EmployeePersonalDetails> employees = myInterface.display();
////                logger.info("Details fetched");
//                groupOfEmployee.setEmployeePersonalDetails(employees);
//            }  catch (Exception e) {
//                // logger.error(e.getMessage() + e.getClass().getName());
//                try {
//                    // Create a SOAPFault instance
//                    SOAPFault soapFault = SOAPFactory.newInstance().createFault();
//                    // Set fault code and message
//                    soapFault.setFaultCode(String.valueOf(500));
//                    soapFault.setFaultString("HTTP STATUS CODE:");
//                    // Throw SOAPFaultException with the SOAPFault
//                    throw new SOAPFaultException(soapFault);
//                } catch (SOAPException ex) {
//                    logger.error(ex.getMessage());
//                }
//            }
//            return groupOfEmployee;
//        }
//
//        @WebMethod
//        @WebResult(name = "ListPincode")
//        public GroupOfEmployee searchByPincode(@WebParam(name = "pincode")Integer pincode) {
//            GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
//            try {
//                List<EmployeePersonalDetails> employees = myInterface.searchByPinCode(pincode);
//                groupOfEmployee.setEmployeePersonalDetails(employees);
//            } catch (Exception e) {
//                // logger.error(e.getMessage() + e.getClass().getName());
//                try {
//                    // Create a SOAPFault instance
//                    SOAPFault soapFault = SOAPFactory.newInstance().createFault();
//                    // Set fault code and message
//                    soapFault.setFaultCode(exceptionHandler(e));
//                    soapFault.setFaultString(e.getMessage());
//                    // Throw SOAPFaultException with the SOAPFault
//                    throw new SOAPFaultException(soapFault);
//                } catch (SOAPException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            return groupOfEmployee;
//        }
//
//
//    }

