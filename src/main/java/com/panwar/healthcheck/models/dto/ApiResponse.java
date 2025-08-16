package com.panwar.healthcheck.models.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
    T data, 
    String message, 
    boolean success, 
    LocalDateTime timestamp,
    String errorCode,
    List<String> errors
) {
    /**
     * Helper method to create success response
     * @param <T>
     * @param data
     * @param message
     * @return
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message, true, LocalDateTime.now(), null, null);
    }
    
    /**
     * Helper method to create success response with just message
     * @param message
     * @return
     */
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(null, message, true, LocalDateTime.now(), null, null);
    }
    
    /**
     * Helper method to create error response
     * @param <T>
     * @param data
     * @param message
     * @return
     */
    public static <T> ApiResponse<T> error(T data, String message) {
        return new ApiResponse<>(data, message, false, LocalDateTime.now(), null, null);
    }
    
    /**
     * Helper method to create error response with error code
     * @param <T>
     * @param message
     * @param errorCode
     * @return
     */
    public static <T> ApiResponse<T> error(String message, String errorCode) {
        return new ApiResponse<>(null, message, false, LocalDateTime.now(), errorCode, null);
    }
    
    /**
     * Helper method to create error response with validation errors
     * @param <T>
     * @param message
     * @param errorCode
     * @param errors
     * @return
     */
    public static <T> ApiResponse<T> error(String message, String errorCode, List<String> errors) {
        return new ApiResponse<>(null, message, false, LocalDateTime.now(), errorCode, errors);
    }
}
