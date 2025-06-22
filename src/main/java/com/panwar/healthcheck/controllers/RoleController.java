package com.panwar.healthcheck.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panwar.healthcheck.common.controllers.GenericCrudController;
import com.panwar.healthcheck.models.dto.ApiResponse;
import com.panwar.healthcheck.models.dto.RoleRequest;
import com.panwar.healthcheck.models.dto.RoleResponse;
import com.panwar.healthcheck.services.RoleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController implements GenericCrudController<RoleRequest, RoleResponse, Long> {
    private final RoleService roleService;
    @Override
    @PostMapping("")
    public RoleResponse create(@RequestBody RoleRequest requestDto) {
        return roleService.create(requestDto);
    }

    @Override
    @GetMapping("/{id}")
    public RoleResponse getById(Long id) {
        return roleService.getById(id);
    }

    @Override
    @GetMapping("")
    public ApiResponse<List<RoleResponse>> getAll() {
        return roleService.getAll();
    }
}
