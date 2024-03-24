package web;

import org.database.DatabaseTarget;
import org.database.StorageTarget;
import org.database.Transaction;
import org.database.UserServices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TransactionSoap {
    public UserServices services;
    public TransactionSoap(){
        StorageTarget storageTarget=new DatabaseTarget();
        services=new UserServices(storageTarget);
    }

    @WebMethod
    @WebResult(name = "GroupOfUsers")
    public GroupTransaction readAllByUsername(@WebParam(name = "String")String username) {
        GroupTransaction groupTransaction = new GroupTransaction();
        List<Transaction> list = services.callFindByUsername(username);
        groupTransaction.setTransactions(list);
        return groupTransaction;

    }
}
