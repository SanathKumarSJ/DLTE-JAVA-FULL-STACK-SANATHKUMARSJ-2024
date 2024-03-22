package com.jdbctemp.template.service;

public class TransactionException extends RuntimeException{
    public TransactionException(){
        super("Credit Card Not available");
    }
    public TransactionException(String info){
        super("Transaction Not available "+info);
    }
}