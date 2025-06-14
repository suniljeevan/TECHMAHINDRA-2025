package com.ams.CONFIG;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ams.SERVICE.CustomUserDetailsService;

import jakarta.servlet.http.HttpServletResponse;
@EnableWebSecurity
@Configuration
public class SecurityConfig {

 @Bean
 public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder();
 }
 @Bean
 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
     return config.getAuthenticationManager();
 }


 @Bean
  UserDetailsService userDetailsService(CustomUserDetailsService customService) {
     return customService;
 }

 @Bean
 DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService customService) {
     DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
     auth.setUserDetailsService(customService);
     auth.setPasswordEncoder(passwordEncoder());
     return auth;
 }
 @Bean
 public AuthenticationSuccessHandler successHandler() {
     return (request, response, authentication) -> {
         String role = authentication.getAuthorities().iterator().next().getAuthority();

         if (role.equals("ROLE_ADMIN")) {
             response.sendRedirect("/admin-dashboard");
         } else if (role.equals("ROLE_STUDENT")) {
             response.sendRedirect("/dashboard");
         } else {
             response.sendRedirect("/");
         }
     };
 }
 @Autowired
 private JwtUtil jwtUtil;

 @Autowired
 private CustomUserDetailsService userDetailsService;
 @Bean
 public JwtFilter jwtAuthFilter() {
     return new JwtFilter(jwtUtil, userDetailsService);
 }



 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     http
         .csrf(csrf -> csrf.disable())
         .authorizeHttpRequests(auth -> auth
             .requestMatchers("/", "/student-register", "/login", "/admin-login", "/css/**", "/js/**").permitAll()
             .requestMatchers("/api/**").permitAll() // JWT API endpoints open for auth (JWT will validate)
             .requestMatchers("/admin-dashboard", "/update-status").hasRole("ADMIN")
             .requestMatchers("/dashboard").hasRole("STUDENT")
             .anyRequest().authenticated()
         )
         // Form login for Thymeleaf frontend
         .formLogin(login -> login
             .loginPage("/login")
             .usernameParameter("email")  // or "email" if you change your form
 		    .passwordParameter("password")
 		    .successHandler(successHandler())
 		    .permitAll()
 		
         )
         // Logout config
         .logout(logout -> logout
             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             .logoutSuccessUrl("/")
             .invalidateHttpSession(true)
             .deleteCookies("JSESSIONID")
             .permitAll()
         )
         // Exception handling: return 401 JSON only for /api/**
         .exceptionHandling(ex -> ex
             .defaultAuthenticationEntryPointFor(
                 (request, response, authException) -> {
                     response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                     response.setContentType("application/json");
                     response.getWriter().write("{\"error\": \"Unauthorized\"}");
                 },
                 new AntPathRequestMatcher("/api/**")
             )
         )
         // Add JWT filter before username/password filter
         .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);

     return http.build();
 
}
}
