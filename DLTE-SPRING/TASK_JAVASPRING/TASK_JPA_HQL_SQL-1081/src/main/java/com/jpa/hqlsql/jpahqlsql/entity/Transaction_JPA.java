package com.jpa.hqlsql.jpahqlsql.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction_JPA {

    @Id
    private Long transactionId;
    private String transactionDate;
    private String transactionBy;
    private Double transactionAmount;
    private String transactionType;

    //    create table Transaction_jdbc(transactionId number,transactionDate date, transactionBy varchar(255) ,transactionTo varchar(255),transactionAmount number,transactionRemarks varchar(255));
    public Transaction_JPA() {
    }

    @Override
    public String toString() {
        return "Transaction_JPA{" +
                "transactionId=" + transactionId +
                ", transactionDate='" + transactionDate + '\'' +
                ", transactionBy='" + transactionBy + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}

