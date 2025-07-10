package com.xhn.exception;

public class ForbiddenException extends LifeMateException {
    public ForbiddenException(String message) {
        super(403, message);
    }
}