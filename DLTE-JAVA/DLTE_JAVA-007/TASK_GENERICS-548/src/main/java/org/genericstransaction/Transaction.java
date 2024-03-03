package org.genericstransaction;

import java.util.Arrays;
import java.util.Date;

public class Transaction {
    public Transaction() {
        System.out.println("Initialize the Transaction properties manually");
    }

    public Transaction(Double transactionAmount, String transactionTo, Date dateOfTransaction, String remarks) {
        this.transactionAmount = transactionAmount;
        this.transactionTo = transactionTo;
        this.dateOfTransaction = dateOfTransaction;
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionAmount=" + transactionAmount +
                ", transactionTo='" + transactionTo + '\'' +
                ", dateOfTransaction=" + dateOfTransaction +
                ", remarks=" + remarks +
                '}';
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    private Double transactionAmount;
    private String transactionTo;
    private Date dateOfTransaction;
    private String remarks="";
}
