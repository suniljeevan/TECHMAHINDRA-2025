package com.ums.CONFIG;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ums.SERVICE.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

 @Bean
 public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder();
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
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     http.csrf(csrf -> csrf.disable())
             .authorizeHttpRequests(auth -> auth
                             .requestMatchers("/login", "/signup").permitAll()
                             .requestMatchers("/images/**", "/css/**", "/js/**", "/webjars/**").permitAll()
                             .requestMatchers("/admin/**").hasRole("ADMIN")
                             .requestMatchers("/faculty/**").hasRole("FACULTY")
                             .requestMatchers("/student/**").hasRole("STUDENT")
                             .anyRequest().authenticated()
             )
             .formLogin(login -> login
                             .loginPage("/login").permitAll()
                             .defaultSuccessUrl("/default", true)
             )
             .logout(logout -> logout.permitAll());

     return http.build();
 }
}

