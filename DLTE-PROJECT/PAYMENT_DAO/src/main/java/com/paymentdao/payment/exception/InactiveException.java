package com.paymentdao.payment.exception;

import javax.validation.Valid;

@Valid
public class InactiveException extends  RuntimeException {
    public InactiveException(String message) {
        super(message);
    }
}