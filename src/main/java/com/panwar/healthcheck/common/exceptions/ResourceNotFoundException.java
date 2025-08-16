package com.panwar.healthcheck.common.exceptions;

import org.springframework.http.HttpStatus;

import com.panwar.healthcheck.common.enums.ErrorCode;

/**
 * Exception thrown when a requested resource is not found
 */
public class ResourceNotFoundException extends BaseCustomException {
    
    public ResourceNotFoundException(String message) {
        super(message, ErrorCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
    
    public ResourceNotFoundException(String resource, String field, Object value) {
        super("%s not found with %s: %s", ErrorCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND, resource, field, value);
    }
    
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, ErrorCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND, cause);
    }
}