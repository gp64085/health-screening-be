package com.panwar.healthcheck.common.enums;

public enum ErrorCode {
    // Validation errors
    VALIDATION_ERROR("VALIDATION_ERROR"),
    INVALID_JSON("INVALID_JSON"),
    INVALID_PARAMETER("INVALID_PARAMETER"),
    
    // Authentication & Authorization errors
    UNAUTHORIZED("UNAUTHORIZED"),
    FORBIDDEN("FORBIDDEN"),
    INVALID_TOKEN("INVALID_TOKEN"),
    TOKEN_EXPIRED("TOKEN_EXPIRED"),
    
    // Resource errors
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND"),
    USER_NOT_FOUND("USER_NOT_FOUND"),
    ROLE_NOT_FOUND("ROLE_NOT_FOUND"),
    
    // Request errors
    BAD_REQUEST("BAD_REQUEST"),
    METHOD_NOT_SUPPORTED("METHOD_NOT_SUPPORTED"),
    ENDPOINT_NOT_FOUND("ENDPOINT_NOT_FOUND"),
    UNSUPPORTED_OPERATION("UNSUPPORTED_OPERATION"),
    
    // Server errors
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"),
    DATABASE_ERROR("DATABASE_ERROR");
    
    private final String code;
    
    ErrorCode(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }
    
    @Override
    public String toString() {
        return code;
    }
}