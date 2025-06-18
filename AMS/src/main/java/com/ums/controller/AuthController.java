package com.ums.controller;

import com.ums.model.Admin;
import com.ums.model.Alumni;
import com.ums.repository.AdminRepository;
import com.ums.repository.AlumniRepository;
import com.ums.service.AdminService;
import com.ums.service.AlumniService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AlumniRepository alumniRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @Autowired
    private AdminService adminService;

    @Autowired
    private AlumniService alumniService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "Register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String role) {

        if (role.equals("ROLE_ADMIN")) {
            Admin admin = new Admin();
            admin.setFullName(name);
            admin.setUsername(username);
            admin.setPassword(passwordEncoder.encode(password));
            admin.setRole("ROLE_ADMIN");
            adminRepository.save(admin);
        } else if (role.equals("ROLE_ALUMNI")) {
            Alumni alumni = new Alumni();
            alumni.setName(name);
            alumni.setUsername(username);
            alumni.setPassword(passwordEncoder.encode(password));
            alumni.setRole("ROLE_ALUMNI");
            alumniRepository.save(alumni);
        }

        return "redirect:/?registered=true";
    }
    
    
   
}

