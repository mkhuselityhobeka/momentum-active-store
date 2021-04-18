package com.active.store.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Insufficient funds")
public class InSufficientActivePointsException extends RuntimeException {
    public InSufficientActivePointsException(String message) {
            super(message);
    }
}
