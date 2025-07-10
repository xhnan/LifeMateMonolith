package com.xhn.exception;

import lombok.Getter;

@Getter
public class LifeMateException extends RuntimeException {
    private final Integer code;

    public LifeMateException(String message) {
        super(message);
        this.code = 500;
    }

    public LifeMateException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}