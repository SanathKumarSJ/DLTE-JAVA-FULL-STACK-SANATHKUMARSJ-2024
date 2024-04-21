package com.paymentdao.payment.exception;

import javax.validation.Valid;

@Valid
public class NotExistException extends  RuntimeException {
    public NotExistException(String message) {
        super(message);
    }
}