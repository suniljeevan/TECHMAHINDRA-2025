package com.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ums.model.Alumni;

import java.util.Optional;

public interface AlumniRepository extends JpaRepository<Alumni, Integer> {
    Optional<Alumni> findByEmail(String email);
    Optional<Alumni> findByUsername(String username);
	Optional<Alumni> findByEmailOrUsername(String email, String username);
}
