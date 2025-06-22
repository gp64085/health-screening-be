package com.panwar.healthcheck.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.panwar.healthcheck.models.entity.Role;
import com.panwar.healthcheck.utils.enums.UserRoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(UserRoleEnum roleName);

}
