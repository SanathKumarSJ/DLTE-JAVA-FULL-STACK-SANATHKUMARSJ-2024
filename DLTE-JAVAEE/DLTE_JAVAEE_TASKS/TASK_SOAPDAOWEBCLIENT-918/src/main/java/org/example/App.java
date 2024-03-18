package org.example;

import web.SOAPService;
import web.SOAPServiceService;
import web.Transaction;
import web.User;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        SOAPServiceService soapService = new SOAPServiceService();
        SOAPService soap = soapService.getSOAPServicePort();

        //Creating new User Account
        User user = new User();
        user.setUsername("Rakesh");
        user.setAddress("Mangalore");
        user.setBalance(15200.00);
        user.setContact(9876543211L);
        user.setEmail("rakeshkumar@gmail.com");
        user.setPassword("rakesH@12345");
//
//        //calling the create account method to store the new user in the database
//        soap.createAccount(user);
//        System.out.println("User added Successfully");

//        //-----------FIND ACCOUNT BY USERNAME--------------------
//        // enter the username
//        String userName="sanath";
//        User user1=soap.readByUsername(userName);
//        System.out.println("User Found --> "+user1.getUsername()+" "+user1.getAddress()+ " "+user1.getBalance());

        //-------------FIND TRANSACTION LIST OF USER----------------------
//        String username = "sanath";
//        // calling the readAllByUsername method
//        List<Transaction> transactions = soap.readAllByUsername(username).getTransactions();
//
//        for (Transaction each: transactions) {
//            System.out.println("The transaction done by "+each.getTransactionDoneBy()+" of amount "+each.getTransactionAmount()+" of type "+each.getTransactionType()+ " on "+each.getTransactionDate());
//        }

    }
}