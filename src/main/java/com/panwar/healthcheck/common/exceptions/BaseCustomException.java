package com.panwar.healthcheck.common.exceptions;

import org.springframework.http.HttpStatus;

import com.panwar.healthcheck.common.enums.ErrorCode;

import lombok.Getter;

/**
 * Base custom exception class that all application-specific exceptions should extend.
 * Provides common functionality and structure for all custom exceptions.
 */
@Getter
public abstract class BaseCustomException extends RuntimeException {
    
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;
    private final Object[] args;
    
    /**
     * Constructor with message and error code
     */
    protected BaseCustomException(String message, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.args = null;
    }
    
    /**
     * Constructor with message, error code, and cause
     */
    protected BaseCustomException(String message, ErrorCode errorCode, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.args = null;
    }
    
    /**
     * Constructor with message, error code, and arguments for message formatting
     */
    protected BaseCustomException(String message, ErrorCode errorCode, HttpStatus httpStatus, Object... args) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.args = args;
    }
    
    /**
     * Get formatted message with arguments
     */
    public String getFormattedMessage() {
        if (args != null && args.length > 0) {
            return String.format(getMessage(), args);
        }
        return getMessage();
    }
}