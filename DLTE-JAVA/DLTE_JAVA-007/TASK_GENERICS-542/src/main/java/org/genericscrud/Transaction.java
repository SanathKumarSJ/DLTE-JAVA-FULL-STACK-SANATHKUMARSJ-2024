package org.genericscrud;

import java.util.Date;

public class Transaction {
    private Long transactionID;
    private Double transactionAmount;
    private Date transactionDate;
    private String transactionTo;

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public Transaction(Long transactionID, Double transactionAmount, Date transactionDate, String transactionTo) {
        this.transactionID = transactionID;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionTo = transactionTo;

    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", transactionAmount=" + transactionAmount +
                ", transactionDate=" + transactionDate +
                ", transactionTo='" + transactionTo + '\'' +
                '}'+'\n';
    }
}
