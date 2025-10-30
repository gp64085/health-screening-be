package com.panwar.healthcheck.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.panwar.healthcheck.models.dto.AdminDashboardUsersResponse;
import com.panwar.healthcheck.models.dto.ApiResponse;
import com.panwar.healthcheck.models.dto.UserResponse;
import com.panwar.healthcheck.repositories.UserRepository;
import com.panwar.healthcheck.utils.ResponseUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DashboardService {
    private final UserRepository userRepository;

    public ResponseEntity<ApiResponse<AdminDashboardUsersResponse>> getAdminDashboardUsersResponse() {
        Pageable pageable = PageRequest.of(0, 20);
        var users = userRepository.findAll(pageable);

        var userResponse = users.getContent().stream().map(user -> new UserResponse((int) user.getId(), user.getName(),
                user.getEmail(), user.getActive(), user.getRole().getName(), user.getCreatedAt().toString())).toList();
        return ResponseUtil.success(new AdminDashboardUsersResponse(userResponse, users.getTotalElements(),
                userRepository.countByActiveTrue(), userRepository.countByDeletedTrue()), "User data fetched successfully");
    }

}
