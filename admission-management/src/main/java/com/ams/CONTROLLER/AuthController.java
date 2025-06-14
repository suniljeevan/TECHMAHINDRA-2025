package com.ams.CONTROLLER;

import com.ams.CONFIG.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"))
            );

            User user = (User) authentication.getPrincipal();
            String role = user.getAuthorities().iterator().next().getAuthority(); // e.g., ROLE_STUDENT
            String token = jwtUtil.generateToken(user.getUsername(), role);
            System.out.println("Attempting login for: " + credentials.get("username"));
            return ResponseEntity.ok(Map.of("token", token, "role", role));
        } catch (AuthenticationException E) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
