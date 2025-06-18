package com.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ums.model.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUsername(String username);
}
