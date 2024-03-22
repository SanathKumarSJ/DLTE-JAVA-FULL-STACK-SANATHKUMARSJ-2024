package com.jdbctemp.template.entity;

import java.sql.Date;

public class Transaction {

    private String transactionId;
    private Date transactionDate;
    private String transactionBy;
    private String transactionTo;
    private Double transactionAmount;
    private String transactionRemarks;

//    create table Transaction_jdbc(transactionId number,transactionDate date, transactionBy varchar(255) ,transactionTo varchar(255),transactionAmount number,transactionRemarks varchar(255));
    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", transactionDate=" + transactionDate +
                ", transactionBy='" + transactionBy + '\'' +
                ", transactionTo='" + transactionTo + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionRemarks='" + transactionRemarks + '\'' +
                '}';
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    public Transaction(String transactionId, Date transactionDate, String transactionBy, String transactionTo, Double transactionAmount, String transactionRemarks) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionBy = transactionBy;
        this.transactionTo = transactionTo;
        this.transactionAmount = transactionAmount;
        this.transactionRemarks = transactionRemarks;
    }
}
