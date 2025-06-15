package com.ums.CONTROLLER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ums.MODEL.User;
import com.ums.REPOSITORY.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
@Controller
public class HomeController {

	@Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole("ROLE_" + role.toUpperCase()); // Example: ROLE_ADMIN
        repo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/admin/index";
        } else if (request.isUserInRole("FACULTY")) {
            return "redirect:faculties/faculty/dashboard";
        } else if (request.isUserInRole("STUDENT")){
            return "redirect:/student/dashboard";
        }
        return "redirect:/login"; 
    }

    @GetMapping("/admin/index")
    public String adminPage() {
        return "index"; // admin page
    }

    @GetMapping("/user/home")
    public String userPage() {
        return "home"; // student/faculty page
    }
}
