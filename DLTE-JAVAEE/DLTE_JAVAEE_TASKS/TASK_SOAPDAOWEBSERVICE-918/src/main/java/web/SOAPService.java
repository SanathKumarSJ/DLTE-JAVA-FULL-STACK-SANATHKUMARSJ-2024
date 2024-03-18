package web;

import org.database.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;


@WebService //webservice
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SOAPService {
    private UserServices userServices;

    public SOAPService() {
        StorageTarget storageTarget = new DatabaseTarget();
        userServices = new UserServices(storageTarget);
    }

    //webmethod i.e creating a new user account
    @WebMethod
    public void createAccount(@WebParam(name = "Username") User user) {
        userServices.callSave(user);
    }


    //webresult o.e the method return result the userlist matched with the name
    @WebMethod
    @WebResult(name = "Username")
    public User readByUsername(@WebParam(name = "name") String userName) {
        return userServices.callFindById(userName);
    }


    // ReadAllByUsername will return the transaction list made by the respected user
    @WebMethod
    @WebResult(name = "GroupOfUsers")
    public GroupTransaction readAllByUsername(@WebParam(name = "String") String username) {
        GroupTransaction groupTransactions = new GroupTransaction();
        List<Transaction> transactionList = userServices.callFindByUsername(username);
        groupTransactions.setTransactions(transactionList);
        return groupTransactions;
    }
}