package com.example.event_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_management_system.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {
}
