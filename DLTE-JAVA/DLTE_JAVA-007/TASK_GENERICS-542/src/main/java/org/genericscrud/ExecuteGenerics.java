package org.genericscrud;

import java.util.Date;

public class ExecuteGenerics {
    public static void main(String[] args) {
        GenericAnalysis<CreditCard> creditCardgenericAnalysis= new GenericAnalysis<>();
        creditCardgenericAnalysis.myObjects= new CreditCard[5];
        CreditCard creditCard1 = new CreditCard(7846535465L,"Sanath",100000,new Date(2024,12,11));
        CreditCard creditCard2 = new CreditCard(1242655465L,"Razzak",80000,new Date(2025,1,01));
        CreditCard creditCard3 = new CreditCard(2525365465L,"Akash",90000,new Date(2024,12,21));
        CreditCard creditCard4 = new CreditCard(4484465465L,"Mahesh",110000,new Date(2024,8,15));
        // Inserting
        System.out.println("----INSERTING----");
        System.out.println(creditCardgenericAnalysis.insertNewRecord(creditCard1));
        System.out.println(creditCardgenericAnalysis.insertNewRecord(creditCard2));
        System.out.println(creditCardgenericAnalysis.insertNewRecord(creditCard3));
        System.out.println(creditCardgenericAnalysis.insertNewRecord(creditCard4));

        //read
        System.out.println("---READING---");
        System.out.println(creditCardgenericAnalysis.read(1));
        // Update
        System.out.println("----UPDATING-----");
        CreditCard creditCard5 = new CreditCard(7554465465L,"Suresh",120000,new Date(2024,7,25));
        creditCardgenericAnalysis.update(5,creditCard5);

        //Delete
        System.out.println("-----DELETING----");
        System.out.println(creditCardgenericAnalysis.delete(1));

        System.out.println("-----VIEW-----");
        //viewall
        creditCardgenericAnalysis.viewAll();

        //--------------------- Transaction-------------------

       GenericAnalysis<Transaction> transactionGenericAnalysis= new GenericAnalysis<>();
        transactionGenericAnalysis.myObjects= new Transaction[5];
        Transaction transaction1 = new Transaction(786546666L,52000.0,new Date(2024,2,13),"Rajesh");
        Transaction transaction2 = new Transaction(5858546666L,57000.0,new Date(2024,4,25),"Karan");
        Transaction transaction3 = new Transaction(141546666L,92000.0,new Date(2024,7,8),"Naresh");
        Transaction transaction4 = new Transaction(8585546666L,82000.0,new Date(2024,9,23),"Sahil");
        // Inserting
        System.out.println("----INSERTING----");
        System.out.println(transactionGenericAnalysis.insertNewRecord(transaction1));
        System.out.println(transactionGenericAnalysis.insertNewRecord(transaction2));
        System.out.println(transactionGenericAnalysis.insertNewRecord(transaction3));
        System.out.println(transactionGenericAnalysis.insertNewRecord(transaction4));

        //read
        System.out.println("---READING---");
        System.out.println(transactionGenericAnalysis.read(1));
        // Update
        System.out.println("----UPDATING-----");
        Transaction transaction5 = new Transaction(9696966L,120000.0,new Date(2024,7,30),"Prashanth");
        transactionGenericAnalysis.update(4,transaction5);

        //Delete
        System.out.println("-----DELETING----");
        System.out.println(transactionGenericAnalysis.delete(3));

        System.out.println("-----VIEW-----");
        //viewall
        transactionGenericAnalysis.viewAll();

    }
    }
