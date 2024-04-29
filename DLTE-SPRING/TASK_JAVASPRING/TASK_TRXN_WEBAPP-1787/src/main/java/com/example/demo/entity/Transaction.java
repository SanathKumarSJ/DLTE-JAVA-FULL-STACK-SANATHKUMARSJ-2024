package com.example.demo.entity;

import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.util.Date;

    @Component
    public class Transaction {
        @NotNull
        @Digits(integer = 3,fraction = 0)
        private Long transactionId;

        @PastOrPresent(message = "{transaction.date}")
        private Date transactionDate;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "{transaction.by}")
        private String transactionBy;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "{transaction.to}")
        private String transactionTo;

        @NotNull
        @DecimalMin(value = "0.01",message = "{transaction.amount}")
        private Double transactionAmount;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "{transaction.remarks}")
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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
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

        public Transaction(@NotNull(message = "{transactionId.notNull}") @Digits(integer = 3, fraction = 0) Long transactionId, @PastOrPresent(message = "{transaction.date}") Date transactionDate, @NotBlank(message = "{transactionFrom.notNull}") String transactionBy, @NotBlank(message = "{transactionTo.notNull}") String transactionTo, @NotNull(message = "{transactionAmount.notnull}") @DecimalMin(value = "0.01", message = "{transactionAmount.not.zero}") Double transactionAmount, @NotBlank(message = "{transactionRemarks.notNull}") @Pattern(regexp = "(?i)friend|bills|family|general", message = "{transaction.type.pattern}") String transactionRemarks) {
            this.transactionId = transactionId;
            this.transactionDate = transactionDate;
            this.transactionBy = transactionBy;
            this.transactionTo = transactionTo;
            this.transactionAmount = transactionAmount;
            this.transactionRemarks = transactionRemarks;
        }
    }
