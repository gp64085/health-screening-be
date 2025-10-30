package com.panwar.healthcheck.models.dto;

import com.panwar.healthcheck.utils.enums.UserRoleEnum;

public record UserResponse(int id, String name, String email, boolean active, UserRoleEnum role, String registeredDate) {

}
