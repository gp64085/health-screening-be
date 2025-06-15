package com.panwar.healthcheck.models.entity;

import com.panwar.healthcheck.utils.enums.UserRoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Role extends AbstractBaseEntity {
	@Column(name = "name", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private UserRoleEnum name;

	@Column(nullable = true)
	private String description;
	
	@Column(nullable = false)
	private Boolean active = true;
}
