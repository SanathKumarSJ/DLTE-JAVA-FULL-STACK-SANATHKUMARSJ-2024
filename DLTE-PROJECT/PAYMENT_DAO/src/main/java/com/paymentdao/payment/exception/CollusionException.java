package com.paymentdao.payment.exception;

import javax.validation.Valid;

@Valid
public class CollusionException extends  RuntimeException {
    public CollusionException(String message) {
        super(message);
    }
}