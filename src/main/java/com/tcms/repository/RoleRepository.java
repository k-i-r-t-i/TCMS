package com.tcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcms.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
