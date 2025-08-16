package com.panwar.healthcheck.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.panwar.healthcheck.common.enums.ErrorCode;
import com.panwar.healthcheck.models.dto.ApiResponse;

public class ResponseUtil {
    
    /**
     * Create a successful response with data
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message) {
        return ResponseEntity.ok(ApiResponse.success(data, message));
    }
    
    /**
     * Create a successful response with just message
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(String message) {
        return ResponseEntity.ok(ApiResponse.success(message));
    }
    
    /**
     * Create a created response (201)
     */
    public static <T> ResponseEntity<ApiResponse<T>> created(T data, String message) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(data, message));
    }
    
    /**
     * Create a bad request response (400)
     */
    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(message, ErrorCode.BAD_REQUEST.getCode()));
    }
    
    /**
     * Create a bad request response with validation errors
     */
    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message, List<String> errors) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(message, ErrorCode.VALIDATION_ERROR.getCode(), errors));
    }
    
    /**
     * Create an unauthorized response (401)
     */
    public static <T> ResponseEntity<ApiResponse<T>> unauthorized(String message) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(message, ErrorCode.UNAUTHORIZED.getCode()));
    }
    
    /**
     * Create a forbidden response (403)
     */
    public static <T> ResponseEntity<ApiResponse<T>> forbidden(String message) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.error(message, ErrorCode.FORBIDDEN.getCode()));
    }
    
    /**
     * Create a not found response (404)
     */
    public static <T> ResponseEntity<ApiResponse<T>> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(message, ErrorCode.RESOURCE_NOT_FOUND.getCode()));
    }
    
    /**
     * Create an internal server error response (500)
     */
    public static <T> ResponseEntity<ApiResponse<T>> internalServerError(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(message, ErrorCode.INTERNAL_SERVER_ERROR.getCode()));
    }
    
    /**
     * Create a custom error response
     */
    public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatus status, String message, ErrorCode errorCode) {
        return ResponseEntity.status(status)
                .body(ApiResponse.error(message, errorCode.getCode()));
    }
}