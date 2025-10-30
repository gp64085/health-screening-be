package com.panwar.healthcheck.models.dto;

import com.panwar.healthcheck.utils.RegexConstants;
import com.panwar.healthcheck.utils.messages.ValidationMessages;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Register Request", example = """
		{
			"name": "John Doe",
			"email": "l2A8V@example.com",
			"password": "password123",
			"mobile": "1234567890"
		}""")
public record RegisterRequest(@NotBlank(message = ValidationMessages.NAME_REQUIRED) String name,
		@NotBlank(message = ValidationMessages.EMAIL_REQUIRED) @Email(message = ValidationMessages.EMAIL_VALIDATION) String email,
		@NotBlank(message = ValidationMessages.PASSWORD_REQUIRED) @Pattern(regexp = RegexConstants.PASSWORD_REGEX, message = ValidationMessages.PASSWORD_VALIDATION) String password,
		@NotBlank(message = ValidationMessages.MOBILE_REQUIRED) @Size(min = 10, max = 15, message = ValidationMessages.MOBILE_VALIDATION) String mobile) {

}
