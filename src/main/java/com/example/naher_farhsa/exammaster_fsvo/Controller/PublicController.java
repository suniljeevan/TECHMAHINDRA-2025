package com.example.naher_farhsa.exammaster_fsvo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exammaster")
public class PublicController {

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/dashboard")
    public String showDashboardPage() {
        return "dashboard";
    }
}
