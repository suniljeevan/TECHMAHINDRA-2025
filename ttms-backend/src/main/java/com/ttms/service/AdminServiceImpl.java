package com.ttms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttms.model.AdminUser;
import com.ttms.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
    
    @Autowired
    private AdminRepository adminRepo;

    @Override
    public String upsert(AdminUser adminUser) {
        adminRepo.save(adminUser);
        return "Success";
    }    

    @Override
    public AdminUser getAdminById(Integer aid) {
        return adminRepo.findById(aid).orElse(null);
    }
    
    @Override
    public List<AdminUser> getAllAdminUsers() {
        return adminRepo.findAll();
    }

    @Override
    public AdminUser findByEmailAndPassword(String email, String password) {
        return adminRepo.findByAdminEmailAndAdminPassword(email, password);
    }
}
