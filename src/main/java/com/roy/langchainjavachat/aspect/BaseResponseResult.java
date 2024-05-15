package com.roy.langchainjavachat.aspect;

import java.beans.ConstructorProperties;
import java.io.Serial;
import java.io.Serializable;

public class BaseResponseResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long code;
    private String msg;
    private T data;

    public static <T> BaseResponseResult<T> success() {
        BaseResponseResult<T> result = new BaseResponseResult<>();
        result.setCode(0L);
        result.setMsg("success");
        return result;
    }

    public static <T> BaseResponseResult<T> success(T data) {
        BaseResponseResult<T> result = new BaseResponseResult<>();
        result.setCode(0L);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static <T> BaseResponseResult<T> fail() {
        BaseResponseResult<T> result = new BaseResponseResult<>();
        result.setCode(1L);
        result.setMsg("系统繁忙");
        return result;
    }

    public static <T> BaseResponseResult<T> fail(Long code, String msg) {
        BaseResponseResult<T> result = new BaseResponseResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public Long getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public BaseResponseResult<T> setCode(final Long code) {
        this.code = code;
        return this;
    }

    public BaseResponseResult<T> setMsg(final String msg) {
        this.msg = msg;
        return this;
    }

    public BaseResponseResult<T> setData(final T data) {
        this.data = data;
        return this;
    }

    public BaseResponseResult() {

    }

    @ConstructorProperties({"code", "msg", "data"})
    public BaseResponseResult(final Long code, final String msg, final T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String toString() {
        return "BaseResponseResult(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }

}