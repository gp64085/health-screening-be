package com.panwar.healthcheck.models.entity;

import jakarta.persistence.Transient;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class User extends AbstractBaseEntity implements UserDetails {

	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	private String mobile;

	@JsonIgnore
	@Column(nullable = false)
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@Column(nullable = false)
	private Boolean active = true;

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(() -> "ROLE_" + role.getName());
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	@JsonIgnore
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return Boolean.TRUE.equals(active);
	}
}
