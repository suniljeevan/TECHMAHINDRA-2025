package com.techm.rpm.REPOSITORY;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techm.rpm.MODEL.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByAdminEmailAndAdminPassword(String email, String password);
}