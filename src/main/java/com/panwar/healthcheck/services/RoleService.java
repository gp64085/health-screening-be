package com.panwar.healthcheck.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.panwar.healthcheck.common.exceptions.ResourceNotFoundException;
import com.panwar.healthcheck.common.services.GenericCrudService;
import com.panwar.healthcheck.models.dto.ApiResponse;
import com.panwar.healthcheck.models.dto.RoleRequest;
import com.panwar.healthcheck.models.dto.RoleResponse;
import com.panwar.healthcheck.models.entity.Role;
import com.panwar.healthcheck.repositories.RoleRepository;
import com.panwar.healthcheck.utils.ResponseUtil;
import com.panwar.healthcheck.utils.enums.UserRoleEnum;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RoleService implements GenericCrudService<RoleRequest, RoleResponse, Long> {
    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<ApiResponse<RoleResponse>> create(RoleRequest requestDto) {
        log.info("Creating new role with name: {}", requestDto.name());

        UserRoleEnum roleEnum = UserRoleEnum.fromString(requestDto.name());

        // Check if role with same name already exists
        if (roleRepository.existsByName(roleEnum)) {
            return ResponseUtil.badRequest("Role with name '" + requestDto.name() + "' already exists");
        }

        try {
            var role = Role.builder()
                    .name(roleEnum)
                    .description(requestDto.description())
                    .active(true)
                    .build();
            var savedRole = roleRepository.save(role);

            log.info("Successfully created role with ID: {}", savedRole.getId());
            return ResponseUtil.created(prepareRoleResponse(savedRole), "Role created successfully");
        } catch (Exception e) {
            log.error("Error creating role: {}", e.getMessage());
            if (e instanceof SQLException && e.getCause() != null && e.getCause().getMessage().contains("duplicate")) {
                log.error("SQL error creating role: {}", e.getCause());
                return ResponseUtil.badRequest("Role with name '" + requestDto.name() + "' already exists");
            }
            return ResponseUtil.badRequest("Failed to create role: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<RoleResponse>> getById(Long id) throws ResourceNotFoundException {
        log.info("Fetching role with ID: {}", id);

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));

        return ResponseUtil.success(prepareRoleResponse(role), "Role retrieved successfully");
    }

    @Override
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getAll() {
        log.info("Fetching all roles");

        try {
            var roles = roleRepository.findAll();
            var rolesList = roles.stream()
                    .map(this::prepareRoleResponse)
                    .toList();

            log.info("Successfully fetched {} roles", rolesList.size());
            return ResponseUtil.success(rolesList, "Roles retrieved successfully");
        } catch (Exception e) {
            log.error("Error fetching roles: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch roles: " + e.getMessage());
        }
    }

    /**
     * Prepares and returns a RoleResponse object from the given Role entity.
     *
     * @param role the Role entity to prepare the RoleResponse from
     * @return the prepared RoleResponse object
     */
    private RoleResponse prepareRoleResponse(Role role) {
        return new RoleResponse(
                role.getName(),
                role.getDescription(),
                role.getActive(),
                role.getCreatedAt(),
                role.getUpdatedAt());
    }
}
