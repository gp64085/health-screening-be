package com.panwar.healthcheck.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "User login Request")
public record LoginRequest(@NotBlank(message = "Username cannot be blank") String username,

		@NotBlank(message = "Password cannot be blank") String password) {
}