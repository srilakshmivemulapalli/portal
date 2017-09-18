package com.nisum.portal.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nisum.portal.data.domain.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{

}
