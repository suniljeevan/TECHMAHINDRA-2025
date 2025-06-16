package com.techm.rpm.SERVICE;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.rpm.MODEL.Admin;
import com.techm.rpm.REPOSITORY.AdminRepository;
@Service
public class AdminService {
	 @Autowired
	    private AdminRepository adminRepository;

	    // Method to authenticate a admin using email and password
	    public Admin authenticateAdmin(String email, String password) {
	        Optional<Admin> adminOptional = adminRepository.findByAdminEmailAndAdminPassword(email, password);
	        if (adminOptional.isPresent()) {
	            return adminOptional.get(); // Return the admin if found
	        } else {
	            // Return null or throw a custom exception (for failed authentication)
	            return null;
	        }
	    }

}
