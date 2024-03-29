package com.transaction.jdbc.demo.DAO;

public class TransactionException extends RuntimeException{
    public TransactionException(String info){
        super("Transaction Not available "+info);
    }
}
