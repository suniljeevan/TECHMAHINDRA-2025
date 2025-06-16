package com.ttms.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7); // Extract the token from the header

            try {
                // Extract username (email) from JWT token
                String email = jwtUtil.extractUsername(token);

                // Validate the token (use your validation logic)
                if (jwtUtil.validateToken(token, email)) {

                    // Set authorities/roles - assuming the user has the "ADMIN" role
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            email, 
                            null, 
                            List.of(
                                    new SimpleGrantedAuthority("Admin"),
                                    new SimpleGrantedAuthority("Faculty"),
                                    new SimpleGrantedAuthority("Student")
                                )
                    );

                    // Set the authentication object in the SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
        }

        // Proceed to the next filter in the chain
        filterChain.doFilter(request, response);
    }
}