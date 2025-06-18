package com.example.event_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String showAdminDashboard() {
        return "admin";  // This returns admin.html from /templates
    }
}

