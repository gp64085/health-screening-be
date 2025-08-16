package com.panwar.healthcheck.models.dto;

import jakarta.validation.constraints.NotNull;

public record RoleRequest(@NotNull(message = "Role name cannot be null")  String name, String description) {

}
