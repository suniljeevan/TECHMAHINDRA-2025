package com.ttms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttms.model.AdminUser;
import com.ttms.security.JwtUtil;
import com.ttms.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminRESTController {
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private JwtUtil jwtUtil;

    // Admin API's
    @PostMapping("/login")
    public ResponseEntity<String> loginAdminUser(@RequestBody AdminUser adminUser) {
        // Check if the admin credentials are valid
        AdminUser existingAdmin = adminService.findByEmailAndPassword(adminUser.getAdminEmail(), adminUser.getAdminPassword());

        if (existingAdmin != null) {
            // If valid, generate JWT token
            String token = jwtUtil.generateToken(existingAdmin.getAdminEmail());

            // Return the token in the response
            return ResponseEntity.ok("Bearer: " + token);  // Send the token with a "Bearer" prefix
        } else {
            // If invalid credentials, return error message
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    // Admin Registration API (for new users)
    @PostMapping("/register")
    public ResponseEntity<String> createAdminUser(@RequestBody AdminUser adminUser) {
        String status = adminService.upsert(adminUser);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("/{aid}")
    public ResponseEntity<AdminUser> getAdminById(@PathVariable Integer aid) {
        AdminUser adminUser = adminService.getAdminById(aid);
        return new ResponseEntity<>(adminUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AdminUser>> getAllAdminUsers() {
        List<AdminUser> allAdminUsers = adminService.getAllAdminUsers();
        return new ResponseEntity<>(allAdminUsers, HttpStatus.OK);
    }
}