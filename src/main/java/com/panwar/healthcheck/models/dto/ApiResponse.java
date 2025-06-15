package com.panwar.healthcheck.models.dto;

import java.time.LocalDateTime;

public record ApiResponse<T>(T data, String message, boolean success, LocalDateTime timestamp) {
	/**
	 * helper method to create success response
	 * @param <T>
	 * @param data
	 * @param message
	 * @return
	 */
	public static <T> ApiResponse<T> success(T data, String message) {
		return new ApiResponse<>(data, message, true, LocalDateTime.now());
	}
	
	/**
	 * helper method to create error response
	 * @param <T>
	 * @param data
	 * @param message
	 * @return
	 */
	public static <T> ApiResponse<T> error(T data, String message) {
		return new ApiResponse<>(data, message, false, LocalDateTime.now());
	}
}
