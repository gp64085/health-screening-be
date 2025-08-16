package com.panwar.healthcheck.common.exceptions;

import org.springframework.http.HttpStatus;

import com.panwar.healthcheck.common.enums.ErrorCode;

/**
 * Exception thrown for bad request scenarios
 */
public class BadRequestException extends BaseCustomException {
    
    public BadRequestException(String message) {
        super(message, ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
    }
    
    public BadRequestException(String message, Throwable cause) {
        super(message, ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, cause);
    }
    
    public BadRequestException(String message, Object... args) {
        super(message, ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, args);
    }
}