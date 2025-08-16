package com.panwar.healthcheck.common.exceptions;

import org.springframework.http.HttpStatus;

import com.panwar.healthcheck.common.enums.ErrorCode;

/**
 * Exception thrown for forbidden access attempts
 */
public class ForbiddenException extends BaseCustomException {
    
    public ForbiddenException(String message) {
        super(message, ErrorCode.FORBIDDEN, HttpStatus.FORBIDDEN);
    }
    
    public ForbiddenException(String message, Throwable cause) {
        super(message, ErrorCode.FORBIDDEN, HttpStatus.FORBIDDEN, cause);
    }
}