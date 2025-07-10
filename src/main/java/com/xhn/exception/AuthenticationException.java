package com.xhn.exception;

public class AuthenticationException extends LifeMateException {
    public AuthenticationException(String message) {
        super(401, message);
    }
}