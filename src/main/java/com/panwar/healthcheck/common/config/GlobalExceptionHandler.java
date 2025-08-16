package com.panwar.healthcheck.common.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.panwar.healthcheck.common.enums.ErrorCode;
import com.panwar.healthcheck.common.exceptions.BaseCustomException;
import com.panwar.healthcheck.models.dto.ApiResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

        /**
         * Handle validation errors
         */
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(
                        MethodArgumentNotValidException ex) {

                List<String> errors = new ArrayList<>();
                ex.getBindingResult().getAllErrors().forEach(error -> {
                        var message = error.getDefaultMessage();
                        if (error instanceof FieldError) {
                                var fieldName = ((FieldError) error).getField();
                                errors.add(fieldName + ": " + message);
                        } else {
                                errors.add(error.getObjectName() + ": " + message);
                        }
                });

                log.error("Validation error: {}", errors);

                ApiResponse<Object> response = ApiResponse.error(
                                "Validation failed",
                                ErrorCode.VALIDATION_ERROR.toString(),
                                errors);

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        /**
         * Handle all custom exceptions that extend BaseCustomException
         * This is the centralized handler for all application-specific exceptions
         */
        @ExceptionHandler(BaseCustomException.class)
        public ResponseEntity<ApiResponse<Object>> handleBaseCustomException(
                        BaseCustomException ex, WebRequest request) {

                log.error("Custom exception occurred: {} - {}", ex.getClass().getSimpleName(),
                                ex.getFormattedMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                ex.getFormattedMessage(),
                                ex.getErrorCode().getCode());

                return new ResponseEntity<>(response, ex.getHttpStatus());
        }

        /**
         * Handle Spring Security BadCredentialsException
         */
        @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity<ApiResponse<Object>> handleBadCredentialsException(
                        BadCredentialsException ex, WebRequest request) {

                log.error("Bad credentials: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                "Invalid credentials",
                                ErrorCode.UNAUTHORIZED.getCode());

                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        /**
         * Handle Spring Security AccessDeniedException
         */
        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(
                        AccessDeniedException ex, WebRequest request) {

                log.error("Access denied: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                "Access denied",
                                ErrorCode.FORBIDDEN.getCode());

                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        /**
         * Handle JWT related exceptions
         */
        @ExceptionHandler({ JwtException.class, ExpiredJwtException.class })
        public ResponseEntity<ApiResponse<Object>> handleJwtException(
                        RuntimeException ex, WebRequest request) {

                log.error("JWT error: {}", ex.getMessage());

                var message = ex instanceof ExpiredJwtException ? "Token has expired" : "Invalid token";

                ApiResponse<Object> response = ApiResponse.error(
                                message,
                                ErrorCode.INVALID_TOKEN.getCode());

                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        /**
         * Handle username not found exceptions
         */
        @ExceptionHandler(UsernameNotFoundException.class)
        public ResponseEntity<ApiResponse<Object>> handleUsernameNotFoundException(
                        UsernameNotFoundException ex, WebRequest request) {

                log.error("User not found: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                "User not found",
                                ErrorCode.USER_NOT_FOUND.getCode());

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        /**
         * Handle HTTP method not supported
         */
        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<ApiResponse<Object>> handleMethodNotSupportedException(
                        HttpRequestMethodNotSupportedException ex, WebRequest request) {

                log.error("Method not supported: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                "HTTP method not supported: " + ex.getMethod(),
                                ErrorCode.METHOD_NOT_SUPPORTED.getCode());

                return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
        }

        /**
         * Handle no handler found exceptions (404)
         */
        @ExceptionHandler(NoHandlerFoundException.class)
        public ResponseEntity<ApiResponse<Object>> handleNoHandlerFoundException(
                        NoHandlerFoundException ex, WebRequest request) {

                log.error("No handler found: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                "Endpoint not found: " + ex.getRequestURL(),
                                ErrorCode.ENDPOINT_NOT_FOUND.getCode());

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        /**
         * Handle HTTP message not readable exceptions
         */
        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ApiResponse<Object>> handleHttpMessageNotReadableException(
                        HttpMessageNotReadableException ex, WebRequest request) {

                log.error("Message not readable: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                "Invalid JSON format or missing request body",
                                "INVALID_JSON");

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        /**
         * Handle method argument type mismatch
         */
        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ApiResponse<Object>> handleMethodArgumentTypeMismatchException(
                        MethodArgumentTypeMismatchException ex, WebRequest request) {

                log.error("Argument type mismatch: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                String.format("Invalid value '%s' for parameter '%s'",
                                                ex.getValue(), ex.getName()),
                                ErrorCode.INVALID_PARAMETER.getCode());

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        /**
         * Handle unsupported operation exceptions
         */
        @ExceptionHandler(UnsupportedOperationException.class)
        public ResponseEntity<ApiResponse<Object>> handleUnsupportedOperationException(
                        UnsupportedOperationException ex, WebRequest request) {

                log.error("Unsupported operation: {}", ex.getMessage());

                ApiResponse<Object> response = ApiResponse.error(
                                ex.getMessage(),
                                ErrorCode.UNSUPPORTED_OPERATION.getCode());

                return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
        }

        /**
         * Handle all other exceptions
         */
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<Object>> handleGlobalException(
                        Exception ex, WebRequest request) {

                log.error("Unexpected error occurred: ", ex);

                ApiResponse<Object> response = ApiResponse.error(
                                "An unexpected error occurred. Please try again later.",
                                ErrorCode.INTERNAL_SERVER_ERROR.getCode());

                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}