package com.panwar.healthcheck.models.dto;

import com.panwar.healthcheck.utils.messages.ValidationMessages;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "User login Request", example = """
		{
			"email": "user@mail.com",
			"password": "John@123"
		}""")
public record LoginRequest(
		@NotBlank(message = ValidationMessages.EMAIL_REQUIRED) @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email") String email,

		@NotBlank(message = ValidationMessages.PASSWORD_REQUIRED) @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "Invalid password format") String password) {
}