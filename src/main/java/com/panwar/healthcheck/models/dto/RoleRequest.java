package com.panwar.healthcheck.models.dto;

import com.panwar.healthcheck.utils.enums.UserRoleEnum;

public record RoleRequest(UserRoleEnum name, String description) {

}
