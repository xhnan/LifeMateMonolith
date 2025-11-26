package com.xhn.exception;

import com.xhn.base.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ApplicationException.class)
    public ResponseResult<Void> handleApplicationException(ApplicationException e) {
        logger.error("业务异常: {}", e.getMessage(), e);
        return ResponseResult.failed(e.getCode(), e.getMessage());
    }



    @ExceptionHandler(BusinessException.class)
    public ResponseResult<Void> handleBusinessException(BusinessException e) {
        logger.error("业务异常: {}", e.getMessage(), e);
        return ResponseResult.failed(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseResult<Void> handleAuthenticationException(AuthenticationException e) {
        return ResponseResult.unauthorized(e.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseResult<Void> handleForbiddenException(ForbiddenException e) {
        return ResponseResult.forbidden();
    }

    @ExceptionHandler(LifeMateException.class)
    public ResponseResult<Void> handleLifeMateException(LifeMateException e) {
        logger.error("LifeMate异常: {}", e.getMessage(), e);
        return ResponseResult.failed(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> handleException(Exception e) {
        logger.error("业务异常: {}", e.getMessage(), e);
        return ResponseResult.error("服务器内部错误：" + e.getMessage());
    }
}