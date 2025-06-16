package com.project.service;

import com.project.model.Admin;
import com.project.repository.AdminRepository;
import com.project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);  // Save the admin in the database
    }

    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);  // Find admin by email
    }
}
