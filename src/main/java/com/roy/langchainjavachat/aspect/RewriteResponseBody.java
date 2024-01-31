package com.roy.langchainjavachat.aspect;

import cn.hutool.json.JSONUtil;
import com.roy.langchainjavachat.annotation.ReWriteBody;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class RewriteResponseBody implements ResponseBodyAdvice<Object> {
    public RewriteResponseBody() {
    }

    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getDeclaringClass().isAnnotationPresent(ReWriteBody.class);
    }

    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            return JSONUtil.toJsonStr(BaseResponseResult.success(body));
        } else {
            return body instanceof BaseResponseResult ? body : BaseResponseResult.success(body);
        }
    }
}