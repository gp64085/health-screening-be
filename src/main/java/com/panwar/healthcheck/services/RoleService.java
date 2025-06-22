package com.panwar.healthcheck.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panwar.healthcheck.common.services.GenericCrudService;
import com.panwar.healthcheck.models.dto.ApiResponse;
import com.panwar.healthcheck.models.dto.RoleRequest;
import com.panwar.healthcheck.models.dto.RoleResponse;
import com.panwar.healthcheck.models.entity.Role;
import com.panwar.healthcheck.repositories.RoleRepository;
import com.panwar.healthcheck.utils.MapperUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService implements GenericCrudService<RoleRequest, RoleResponse, Long> {
    private final RoleRepository roleRepository;
    
    
    @Override
    public RoleResponse create(RoleRequest requestDto) {
        var role = Role.builder().name(requestDto.name()).description(requestDto.description()).active(true).build();
        var savedRole = roleRepository.save(role);
        return MapperUtil.map(savedRole, RoleResponse.class);
    }

    @Override
    public RoleResponse getById(Long id) {
        return MapperUtil.map(roleRepository.findById(id).orElseThrow(), RoleResponse.class);
    }

    @Override
    public ApiResponse<List<RoleResponse>> getAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponse> rolesList = roles.stream().map(role -> new RoleResponse(role.getName(), role.getDescription(), role.getActive(), role.getCreatedAt(), role.getUpdatedAt())).toList();
        return ApiResponse.success(rolesList, "Roles List");
        		
    }
}
