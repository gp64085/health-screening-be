package com.panwar.healthcheck.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panwar.healthcheck.models.dto.ApiResponse;
import com.panwar.healthcheck.models.dto.AuthResponse;
import com.panwar.healthcheck.models.dto.RegisterRequest;
import com.panwar.healthcheck.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;


@Tag(name = "Authentication", description = "Handles login and registration")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@Operation(description = "Register a new user", method = "POST")
	@PostMapping("register")
	public ApiResponse<AuthResponse> create(@RequestBody RegisterRequest requestDto) {
		return authService.register(requestDto);
	}
	
}
