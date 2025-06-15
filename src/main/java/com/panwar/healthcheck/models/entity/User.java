package com.panwar.healthcheck.models.entity;

import com.panwar.healthcheck.utils.RegexConstants;
import com.panwar.healthcheck.utils.messages.ValidationMessages;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User extends AbstractBaseEntity {

	@NotBlank(message = ValidationMessages.NAME_REQUIRED)
	@Size(min = 2, max = 50, message = ValidationMessages.NAME_VALIDATION)
	private String name;

	@NotBlank(message = ValidationMessages.EMAIL_REQUIRED)
	@Email(message = ValidationMessages.EMAIL_VALIDATION)
	@Column(unique = true)
	private String email;

	@NotBlank(message = ValidationMessages.MOBILE_REQUIRED)
	@Size(min = 10, max = 15, message = ValidationMessages.MOBILE_VALIDATION)
	private String mobile;

	@NotBlank(message = ValidationMessages.PASSWORD_REQUIRED)
	@Pattern(regexp = RegexConstants.PASSWORD_REGEX, message = ValidationMessages.PASSWORD_VALIDATION)
	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
	
	@Column(nullable = false)
	private Boolean active;
	
}
