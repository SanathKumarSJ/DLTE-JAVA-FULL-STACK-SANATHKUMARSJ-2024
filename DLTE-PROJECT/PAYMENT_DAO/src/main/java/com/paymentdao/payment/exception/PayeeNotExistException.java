package com.paymentdao.payment.exception;

import javax.validation.Valid;

@Valid
public class PayeeNotExistException extends  RuntimeException {
    public PayeeNotExistException(String message) {
        super(message);
    }
}