package com.example.naher_farhsa.exammaster_fsvo.Controller;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.example.naher_farhsa.exammaster_fsvo.Service.JwtService;
import com.example.naher_farhsa.exammaster_fsvo.config.PasswordEncoderConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService ,PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes
    ) {
        System.out.println(username+"      "+password+" Entered here ");

        System.out.println(passwordEncoder.encode("admin123"));

        try {
            // 1. Authenticate user
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // 2. Generate JWT Token
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(userDetails);

            System.out.println("token is   "+token);

            // 3. Set JWT as HttpOnly cookie
            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true); // Only for HTTPS
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60); // 1 day
            response.addCookie(cookie);

            System.out.println("correct password entered ");

            // 4. Check role of user and redirect to /exammaster/dashboard and /exammaster/student-dashboard
            return "redirect:/exammaster/dashboard";

        } catch (AuthenticationException e) {
            // If login fails, redirect back with error message
            System.out.println("wrong password entered ");
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/exammaster/login";
        }
    }



}

