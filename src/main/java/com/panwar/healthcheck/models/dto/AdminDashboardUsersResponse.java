package com.panwar.healthcheck.models.dto;

import java.util.List;

public record AdminDashboardUsersResponse(List<UserResponse> users, long totalUsers, int activeUsers, int deletedUsers) {

}
