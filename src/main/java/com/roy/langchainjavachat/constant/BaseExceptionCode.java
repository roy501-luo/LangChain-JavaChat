package com.roy.langchainjavachat.constant;


public enum BaseExceptionCode {
    SYSTEM_EX(9999L, "系统错误，请查看控制台异常信息");

    private final Long code;
    private final String description;

    BaseExceptionCode(Long code, String description) {
        this.code = code;
        this.description = description;
    }

    public Long getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
