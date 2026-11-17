package com.arc.gpts.tech.bdddemo.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message,  Throwable cause) {
        super(message, cause);
    }
}
