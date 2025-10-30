package com.panwar.healthcheck.models.dto;

import com.panwar.healthcheck.utils.enums.UserRoleEnum;
import com.panwar.healthcheck.utils.messages.ValidationMessages;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "A request to create a new role", example = """
        {
            "name": "ADMIN",
            "description": "Admin role"
        }
            """)
public record RoleRequest(@NotNull(message = ValidationMessages.INVALID_NAME) UserRoleEnum name, String description) {

}
