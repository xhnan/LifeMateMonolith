package com.xhn.exception;

public class BusinessException extends LifeMateException {
    public BusinessException(String message) {
        super(400, message);
    }
}