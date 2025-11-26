package com.xhn.exception;

import lombok.Getter;

import java.io.Serial;

/**
 * 应用级运行时异常，支持可选的业务错误码与链式原因。
 */
@Getter
public class ApplicationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 可选的业务错误码（例如 HTTP 状态或自定义错误码），如果无则为 null。
     */
    private final Integer code;

    public ApplicationException(String message) {
        this(message, null, 500);
    }

    public ApplicationException(String message, Integer code) {
        this(message, null, code);
    }

    public ApplicationException(String message, Throwable cause) {
        this(message, cause, 500);
    }

    public ApplicationException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public ApplicationException(Throwable cause) {
        this(cause == null ? null : cause.toString(), cause, null);
    }

}
