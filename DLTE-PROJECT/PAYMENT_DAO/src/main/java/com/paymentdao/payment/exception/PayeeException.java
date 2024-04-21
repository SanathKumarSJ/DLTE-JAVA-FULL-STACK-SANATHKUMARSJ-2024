package com.paymentdao.payment.exception;

import javax.validation.Valid;

@Valid
public class PayeeException extends  RuntimeException {
    public PayeeException(String message) {
        super(message);
    }
}
