import web.GroupTransaction;
import web.Transaction;
import web.TransactionSoap;
import web.TransactionSoapService;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        //creating object of TransactionSoapService
        TransactionSoapService transactionSoapService= new TransactionSoapService();

        //Invoking TransactionSoap Interface
        TransactionSoap transactionSoap= transactionSoapService.getTransactionSoapPort();

        //Enter the user name to be find the transaction
        String name="sanath";

        //storing the read values in transactionList
        List<Transaction> transactionList = transactionSoap.readAllByUsername(name).getTransactions();

        //displaying the transaction output of the respected user
        for (Transaction each: transactionList) {
            System.out.println("The transaction done by "+each.getTransactionDoneBy()+" of amount "+each.getTransactionAmount()+" of type "+each.getTransactionType()+ " on "+each.getTransactionDate());
        }
    }
}
