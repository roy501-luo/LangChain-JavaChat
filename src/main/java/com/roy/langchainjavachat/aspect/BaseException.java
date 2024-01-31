package com.roy.langchainjavachat.aspect;

import com.roy.langchainjavachat.constant.BaseExceptionCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1699933182520992595L;
    private final Long code;
    private final String description;

    public BaseException(Long code, String description) {
        super(description);
        this.code = code;
        this.description = description;
    }

    public BaseException(BaseExceptionCode exceptionCode) {
        super(exceptionCode.getDescription());
        this.code = exceptionCode.getCode();
        this.description = exceptionCode.getDescription();
    }

    public BaseException(BaseExceptionCode exceptionCode, String description) {
        super(description);
        this.code = exceptionCode.getCode();
        this.description = description;
    }

}
