package com.org.TMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.org.TMS.model.User;
import com.org.TMS.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

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
        user.setPassword(password);
        user.setRole("ROLE_" + role.toUpperCase());
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}
