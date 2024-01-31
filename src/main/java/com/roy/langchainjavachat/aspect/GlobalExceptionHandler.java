package com.roy.langchainjavachat.aspect;

import com.roy.langchainjavachat.constant.BaseExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponseResult<Void> baseExceptionHandler(BaseException e) {
        log.error("BaseException", e);
        return BaseResponseResult.fail(e.getCode(), e.getDescription());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponseResult<Void> exceptionHandler(Exception e) {
        log.error("Exception", e);
        return BaseResponseResult.fail(BaseExceptionCode.SYSTEM_EX.getCode(), BaseExceptionCode.SYSTEM_EX.getDescription());
    }

}
