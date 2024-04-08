package com.paymentdao.payment.entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Payee {

    @NotNull(message = "{payee.id.null}")
    @Digits(integer = 3, fraction = 0, message = "{payee.id}")
    private Integer payeeId;

    @NotNull(message = "{payee.senderAcc.null}")
    @Digits(integer = 12, fraction = 0, message = "{payee.senderAcc}")
    private Long senderAccountNumber;

    @NotNull(message = "{payee.payeeAcc.null}")
    @Digits(integer = 12, fraction = 0, message = "{payee.payeeAcc}")
    private Long payeeAccountNumber;

    @NotNull(message = "{name.null}")
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "{payee.holder}")
    private String payeeName;


    public Payee() {
    }

    public Payee(Integer payeeId, Long senderAccountNumber, Long payeeAccountNumber, String payeeName) {
        this.payeeId = payeeId;
        this.senderAccountNumber = senderAccountNumber;
        this.payeeAccountNumber = payeeAccountNumber;
        this.payeeName = payeeName;
    }

    public Integer getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Integer payeeId) {
        this.payeeId = payeeId;
    }

    public Long getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(Long senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public Long getPayeeAccountNumber() {
        return payeeAccountNumber;
    }

    public void setPayeeAccountNumber(Long payeeAccountNumber) {
        this.payeeAccountNumber = payeeAccountNumber;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }
}
