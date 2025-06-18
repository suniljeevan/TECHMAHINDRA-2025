package com.example.event_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.event_management_system.model.Admin;
import com.example.event_management_system.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Admin> findById(String userId) {
        return adminRepo.findById(userId);
    }

    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public Admin addAdmin(Admin admin) {
        if (adminRepo.existsById(admin.getUserId())) {
            throw new IllegalArgumentException("Admin already exists with userId: " + admin.getUserId());
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepo.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

    public Admin updateAdmin(Admin admin) {
        Optional<Admin> existing = adminRepo.findById(admin.getUserId());
        if (existing.isPresent()) {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            return adminRepo.save(admin);
        } else {
            throw new RuntimeException("Admin not found.");
        }
    }

    public void deleteAdmin(String userId) {
        if ("admin".equalsIgnoreCase(userId)) {
            throw new IllegalArgumentException("Default admin cannot be deleted.");
        }
        adminRepo.deleteById(userId);
    }
}
