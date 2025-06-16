package com.ttms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ttms.model.AdminUser;

public interface AdminRepository extends JpaRepository<AdminUser, Integer> {

    public AdminUser findByAdminEmailAndAdminPassword(String email, String password);
}
