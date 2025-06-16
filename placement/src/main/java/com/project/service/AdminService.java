package com.project.service;

import com.project.model.Admin;

public interface AdminService {

    Admin createAdmin(Admin admin);  // Method to create a new admin
    Admin findByEmail(String email);  // Method to find admin by email
}
