package com.paymentdao.payment.exception;

import javax.validation.Valid;

@Valid
public class PayeeExistException extends  RuntimeException {
    public PayeeExistException(String message) {
        super(message);
    }
}