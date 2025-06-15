package com.panwar.healthcheck.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.panwar.healthcheck.common.GenericCrudService;
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
        var role = Role.builder().name(requestDto.name()).description(requestDto.description()).build();
        var savedRole = roleRepository.save(role);
        return MapperUtil.map(savedRole, RoleResponse.class);
    }

    @Override
    public RoleResponse getById(Long id) {
        return MapperUtil.map(roleRepository.findById(id).orElseThrow(), RoleResponse.class);
    }

    @Override
    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(role -> MapperUtil.map(role, RoleResponse.class)).toList();
    }
}
