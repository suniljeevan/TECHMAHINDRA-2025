package com.org.TMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDashboardController {

    @GetMapping("/userDashboard")
    public String userDashboard() {
        return "userDashboard"; // Thymeleaf template for the user dashboard
    }
}
