package com.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.entity.CustomUser;

public interface UserRepository extends JpaRepository<CustomUser, String> {
	public CustomUser findByEmail(String email);
	Optional<CustomUser> findById(String id);
}
