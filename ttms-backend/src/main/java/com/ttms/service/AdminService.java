package com.ttms.service;

import java.util.List;

import com.ttms.model.AdminUser;

public interface AdminService {
    public String upsert(AdminUser adminUser);
    
    public AdminUser getAdminById(Integer aid);
    
    public List<AdminUser> getAllAdminUsers();

    // New method for checking email and password
    public AdminUser findByEmailAndPassword(String email, String password);
}
