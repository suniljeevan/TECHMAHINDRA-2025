package com.ums.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ums.model.Admin;
import com.ums.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin insertAdmin(Admin admin) {
        return adminRepo.save(admin);
    }

    @Override
    public Admin updateAdmin(Admin admin, int id) {
        Optional<Admin> optionalAdmin = adminRepo.findById(id);
        if (optionalAdmin.isPresent()) {
            Admin existingAdmin = optionalAdmin.get();
            existingAdmin.setUsername(admin.getUsername());
            if (admin.getPassword() != null && !admin.getPassword().isBlank()) {
                existingAdmin.setPassword(admin.getPassword());
            }

            existingAdmin.setFullName(admin.getFullName());
            return adminRepo.save(existingAdmin);
        } else {
            throw new RuntimeException("Admin not found with ID: " + id);
        }
    }

    @Override
    public void deleteAdmin(int id) {
        adminRepo.deleteById(id);
    }

    @Override
    public List<Admin> fetchAllAdmins() {
        return adminRepo.findAll();
    }

    @Override
    public Admin fetchAdminById(int id) {
        return adminRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
    }

    @Override
    public Admin fetchAdminByUsername(String username) {
        return adminRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Admin not found with username: " + username));
    }
    
    @Override
    public Admin authenticate(String username, String password) {
    	Optional<Admin> optionalAdmin = adminRepo.findByUsername(username);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            if (passwordEncoder.matches(password, admin.getPassword())) {
                return admin;
            }
        }
        return null;
    }
}
