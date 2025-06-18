package com.ums.service;

import java.util.List;
import com.ums.model.Admin;

public interface AdminService {
    Admin insertAdmin(Admin admin);
    Admin updateAdmin(Admin admin, int id);
    void deleteAdmin(int id);
    List<Admin> fetchAllAdmins();
    Admin fetchAdminById(int id);
    Admin fetchAdminByUsername(String username);
    Admin authenticate(String username, String password);
}
