package com.ums.controller;

import com.ums.model.Admin;
import com.ums.model.Alumni;
import com.ums.service.AdminService;
import com.ums.service.AlumniService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AlumniService alumniService;
    
    @Autowired
    private AdminService adminService;
    
    // Serve the main home page
    @GetMapping("/")
    public String homepage(@RequestParam(value = "error", required = false) String error,
                           Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password");
        }
        return "homepage"; // matches homepage.html
    }

    @GetMapping("/default")
    public String defaultAfterLogin(Authentication authentication, HttpSession session) {
        String usernameOrEmail = authentication.getName();
        System.out.println("Logged in as: " + usernameOrEmail);
        
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            Admin admin = adminService.fetchAdminByUsername(usernameOrEmail);
            System.out.println("Admin resolved: " + (admin != null ? admin.getUsername() : "null"));
            session.setAttribute("loggedInAdmin", admin); // ✅ Required for session checks in AdminController
            return "redirect:/admin/dashboard";
        }

        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ALUMNI"))) {
            Alumni alumni;
            try {
                alumni = alumniService.fetchAlumniByEmail(usernameOrEmail);
            } catch (Exception e) {
                alumni = alumniService.fetchAlumniByUsername(usernameOrEmail);
            }

            session.setAttribute("loggedInAlumni", alumni); // ✅ Required for session checks in AlumniController

            if (alumni.isPasswordChangeRequired()) {
                return "redirect:/alumni/changepassword";
            }

            return "redirect:/Alumni/dashboard";
        }

        return "redirect:/";
    }


    // ✅ Public alumni directory
    @GetMapping("/view-alumni")
    public String showPublicAlumniDirectory(Model model) {
        List<Alumni> alumniList = alumniService.fetchAllAlumni();
        model.addAttribute("allAlumni", alumniList);
        System.out.println("✅ /view-alumni called");
        return "PublicAlumniDirectory"; // Match this with your template file name
    }
}
