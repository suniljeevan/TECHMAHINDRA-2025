package com.admin.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.admin.scholarship.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@CrossOrigin(origins="*")
public class LoginController {
	
	@Autowired
	private JwtUtil jwtUtil;


    @GetMapping("/")
    public String showLoginPage() {
        return "login"; 
    }

    @PostMapping("/login-handler")
    public String loginHandler(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String role,
                               HttpServletResponse response,
                               HttpSession session) {


        // Generate token
        String token = jwtUtil.generateToken(role.toUpperCase());

        session.setAttribute("JWT_TOKEN", token);
        session.setAttribute("ROLE", role.toUpperCase());

        if ("ADMIN".equalsIgnoreCase(role)) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/student/home";
        }
    }
}    
