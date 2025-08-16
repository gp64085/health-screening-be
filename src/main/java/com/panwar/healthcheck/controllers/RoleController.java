package com.panwar.healthcheck.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panwar.healthcheck.common.controllers.GenericCrudController;
import com.panwar.healthcheck.common.exceptions.ResourceNotFoundException;
import com.panwar.healthcheck.models.dto.ApiResponse;
import com.panwar.healthcheck.models.dto.RoleRequest;
import com.panwar.healthcheck.models.dto.RoleResponse;
import com.panwar.healthcheck.services.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
@Tag(name = "Role", description = "Role related operations")
public class RoleController implements GenericCrudController<RoleRequest, RoleResponse, Long> {

    private final RoleService roleService;

    @Operation(summary = "Create a role", description = "Create a new role")
    @Override
    @PostMapping()
    public ResponseEntity<ApiResponse<RoleResponse>> create(@RequestBody RoleRequest requestDto) {
        return roleService.create(requestDto);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get role by id", description = "Get role by id", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = RoleResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found")
    })
    public ResponseEntity<ApiResponse<RoleResponse>> getById(Long id) throws ResourceNotFoundException {
        return roleService.getById(id);
    }

    @Operation(summary = "Get all roles", description = "Get all roles")
    @Override
    @GetMapping()
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getAll() {
        return roleService.getAll();
    }

}

