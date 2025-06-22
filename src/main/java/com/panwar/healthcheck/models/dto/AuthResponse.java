package com.panwar.healthcheck.models.dto;

import com.panwar.healthcheck.models.entity.User;

public record AuthResponse(String token, User user) {
}
