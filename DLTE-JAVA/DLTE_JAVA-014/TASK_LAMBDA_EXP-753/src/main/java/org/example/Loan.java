package org.example;

import javax.print.DocFlavor;
import java.util.Date;

public class Loan {
    private Long loanNumber;
    private Double loanAmount;
    private Date loanDate;
    private String loanStatus;
    private String loanHolder;
    private Long holderContact;

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                ", loanDate=" + loanDate +
                ", loanStatus='" + loanStatus + '\'' +
                ", loanHolder='" + loanHolder + '\'' +
                ", holderContact=" + holderContact +
                '}';
    }

    public Long getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(Long loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getLoanHolder() {
        return loanHolder;
    }

    public void setLoanHolder(String loanHolder) {
        this.loanHolder = loanHolder;
    }

    public Long getHolderContact() {
        return holderContact;
    }

    public void setHolderContact(Long holderContact) {
        this.holderContact = holderContact;
    }

    public Loan(Long loanNumber, Double loanAmount, Date loanDate, String loanStatus, String loanHolder, Long holderContact) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.loanStatus = loanStatus;
        this.loanHolder = loanHolder;
        this.holderContact = holderContact;
    }
}
