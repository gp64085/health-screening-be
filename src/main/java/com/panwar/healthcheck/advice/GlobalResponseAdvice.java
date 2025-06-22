package com.panwar.healthcheck.advice;

import com.panwar.healthcheck.models.dto.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class converterType) {
        // Apply to all responses
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType,
                                  @NonNull Class selectedConverterType, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        // Avoid double-wrapping
        if (body instanceof ApiResponse) {
            return body;
        }
        // Optionally, skip wrapping for error responses (e.g., ProblemDetail, etc.)
        if (body != null && body.getClass().getSimpleName().equals("ProblemDetail")) {
            return body;
        }
        return ApiResponse.success(body, "Request successful");
    }
}
