package com.paymentdao.payment.entity;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Payee {

    private Integer payeeId;

    private Long senderAccountNumber;

    @Range(min = 100000000000L, max = 999999999999L,message = "{EXB001}")
    @NotNull(message = "{EXB001}")
    @Digits(integer = 12, fraction = 0, message = "{EXB001}")
    private Long payeeAccountNumber;

    @NotNull(message = "{EXB002}")
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "{EXB002}")
    private String payeeName;

    public Payee() {
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



    public Payee(Integer payeeId, Long senderAccountNumber, Long payeeAccountNumber, String payeeName) {
        this.payeeId = payeeId;
        this.senderAccountNumber = senderAccountNumber;
        this.payeeAccountNumber = payeeAccountNumber;
        this.payeeName = payeeName;
    }
}
