package com.panwar.healthcheck.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.panwar.healthcheck.models.dto.AdminDashboardUsersResponse;
import com.panwar.healthcheck.models.dto.ApiResponse;
import com.panwar.healthcheck.services.DashboardService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@Tag(name = "Admin Dashboard", description = "Admin Dashboard APIs")
@AllArgsConstructor
public class AdminDashboardController {
    private final DashboardService dashboardService;
    
    // Get Admin Dashboard Page data like user data 
    @GetMapping("/admin/dashboard/user-data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<AdminDashboardUsersResponse>> getDashBoardPageDetails() {
        return dashboardService.getAdminDashboardUsersResponse();
    }
    
}
