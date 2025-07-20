package com.panwar.healthcheck.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.panwar.healthcheck.common.config.jwt.JwtService;
import com.panwar.healthcheck.models.dto.ApiResponse;
import com.panwar.healthcheck.models.dto.AuthResponse;
import com.panwar.healthcheck.models.dto.LoginRequest;
import com.panwar.healthcheck.models.dto.RegisterRequest;
import com.panwar.healthcheck.models.entity.Role;
import com.panwar.healthcheck.models.entity.User;
import com.panwar.healthcheck.repositories.RoleRepository;
import com.panwar.healthcheck.repositories.UserRepository;
import com.panwar.healthcheck.utils.enums.UserRoleEnum;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final JwtService jwtService;

	public ApiResponse<AuthResponse> register(RegisterRequest requestDto) {
		Role role = roleRepository.findByName(UserRoleEnum.ADMIN).orElse(null);
		User user = User.builder().name(requestDto.name()).email(requestDto.email())
				.password(passwordEncoder.encode(requestDto.password())).mobile(requestDto.mobile()).active(true)
				.role(role).build();

		user = userRepository.save(user);
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role.getName());

		String token = jwtService.generateToken(claims, user.getEmail());

		return ApiResponse.success(new AuthResponse(token, user), "User registered successfully");
	}

	public ApiResponse<AuthResponse> login(LoginRequest requestDto) {
		User user = userRepository.findByEmail(requestDto.email()).orElse(null);
		if (user != null && passwordEncoder.matches(requestDto.password(), user.getPassword())) {
			Map<String, Object> claims = new HashMap<>();
			claims.put("role", user.getRole().getName());

			String token = jwtService.generateToken(claims, user.getEmail());
			return ApiResponse.success(new AuthResponse(token, user), "User logged in successfully");
		}
		return null;
	}
}
