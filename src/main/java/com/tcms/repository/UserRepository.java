package com.tcms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
		boolean existsByUsername(String username);
		  User findByEmail(String email);
		//User findByUsername(String username);
		//Optional<User>findByEmail(String email);
}

