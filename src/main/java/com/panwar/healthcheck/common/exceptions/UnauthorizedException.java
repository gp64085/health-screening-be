package com.panwar.healthcheck.common.exceptions;

import org.springframework.http.HttpStatus;

import com.panwar.healthcheck.common.enums.ErrorCode;

/**
 * Exception thrown for unauthorized access attempts
 */
public class UnauthorizedException extends BaseCustomException {

    public UnauthorizedException(String message) {
        super(message, ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED, cause);
    }
}