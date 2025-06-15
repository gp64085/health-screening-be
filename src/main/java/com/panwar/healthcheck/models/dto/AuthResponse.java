package com.panwar.healthcheck.models.dto;

import java.time.LocalDateTime;

public record AuthResponse<T>(T data, String message, boolean success, LocalDateTime timestamp) {
    static <T> AuthResponse<T> success(T data, String message) {
        return new AuthResponse<>(data, message, true, LocalDateTime.now());
    }

    static <T> AuthResponse<T> error(T data, String message) {
        return new AuthResponse<>(data, message, false, LocalDateTime.now());
    }
}
