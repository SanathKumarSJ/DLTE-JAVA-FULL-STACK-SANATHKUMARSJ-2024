package org.example.xml;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "transaction")
public class Transaction {
    public Transaction(){
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

    @XmlElement(name = "money")
    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @XmlAttribute(name = "TransactionPerson")
    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    @XmlElement(name = "date")
    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    @XmlElement(name = "remark")
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
