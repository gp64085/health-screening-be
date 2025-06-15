package com.panwar.healthcheck.models.dto;

import java.time.LocalDateTime;

import com.panwar.healthcheck.utils.enums.UserRoleEnum;

public record RoleResponse(UserRoleEnum name, String description, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {

}
