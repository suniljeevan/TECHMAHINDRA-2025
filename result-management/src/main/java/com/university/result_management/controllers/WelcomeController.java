package com.university.result_management.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	@GetMapping("/")
	public String home() {
	    System.out.println(">> home controller hit");
	    return "html/home";
	}
	@GetMapping("/login")
    public String loginPage() {
		System.out.println(">> login controller hit");
        return "html/commonlogin"; // resolves to login.jsp
    }

    @GetMapping("/default")
    public String defaultAfterLogin(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard";
        } else if (role.equals("ROLE_INSTRUCTOR")) {
            return "redirect:/instructor/dashboard";
        } else if (role.equals("ROLE_STUDENT")) {
            return "redirect:/student/dashboard";
        } else {
            return "redirect:/login?error";
        }
    }



	   
}
