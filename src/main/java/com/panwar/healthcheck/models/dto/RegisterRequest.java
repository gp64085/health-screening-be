package com.panwar.healthcheck.models.dto;

import com.panwar.healthcheck.utils.RegexConstants;
import com.panwar.healthcheck.utils.messages.ValidationMessages;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotBlank(message = ValidationMessages.NAME_REQUIRED) String name,
		@NotBlank(message = ValidationMessages.EMAIL_REQUIRED) @Email(message = ValidationMessages.EMAIL_VALIDATION) String email,
		@NotBlank(message = ValidationMessages.PASSWORD_REQUIRED) @Pattern(regexp = RegexConstants.PASSWORD_REGEX, message = ValidationMessages.PASSWORD_VALIDATION) String password,
		@NotBlank(message = ValidationMessages.MOBILE_REQUIRED) @Size(min = 10, max = 15, message = ValidationMessages.MOBILE_VALIDATION) String mobile) {

}
