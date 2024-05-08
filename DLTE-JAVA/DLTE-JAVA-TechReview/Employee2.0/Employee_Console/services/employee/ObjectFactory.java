
package services.employee;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the services.employee package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: services.employee
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NewEmployeeRequest }
     * 
     */
    public NewEmployeeRequest createNewEmployeeRequest() {
        return new NewEmployeeRequest();
    }

    /**
     * Create an instance of {@link EmployeePersonal }
     * 
     */
    public EmployeePersonal createEmployeePersonal() {
        return new EmployeePersonal();
    }

    /**
     * Create an instance of {@link GetEmployeeByPinCodeResponse }
     * 
     */
    public GetEmployeeByPinCodeResponse createGetEmployeeByPinCodeResponse() {
        return new GetEmployeeByPinCodeResponse();
    }

    /**
     * Create an instance of {@link ServiceStatus }
     * 
     */
    public ServiceStatus createServiceStatus() {
        return new ServiceStatus();
    }

    /**
     * Create an instance of {@link FindAllEmployeeRequest }
     * 
     */
    public FindAllEmployeeRequest createFindAllEmployeeRequest() {
        return new FindAllEmployeeRequest();
    }

    /**
     * Create an instance of {@link FindAllEmployeeResponse }
     * 
     */
    public FindAllEmployeeResponse createFindAllEmployeeResponse() {
        return new FindAllEmployeeResponse();
    }

    /**
     * Create an instance of {@link GetEmployeeByPinCodeRequest }
     * 
     */
    public GetEmployeeByPinCodeRequest createGetEmployeeByPinCodeRequest() {
        return new GetEmployeeByPinCodeRequest();
    }

    /**
     * Create an instance of {@link NewEmployeeResponse }
     * 
     */
    public NewEmployeeResponse createNewEmployeeResponse() {
        return new NewEmployeeResponse();
    }

    /**
     * Create an instance of {@link EmployeeAddress }
     * 
     */
    public EmployeeAddress createEmployeeAddress() {
        return new EmployeeAddress();
    }

}
