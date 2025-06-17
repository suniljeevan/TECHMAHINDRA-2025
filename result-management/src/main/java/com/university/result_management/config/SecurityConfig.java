package com.university.result_management.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.university.result_management.services.UserDetailServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // disable CSRF for simplicity
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/", "/login", "/error", "/css/**", "/js/**", "/images","/jsp/**","/html/**","/WEB-INF/**").permitAll()  // <-- allow public access
            	    .requestMatchers("/admin/**").hasRole("ADMIN")
            	    .requestMatchers("/instructor/**").hasRole("INSTRUCTOR")
            	    .requestMatchers("/student/**").hasRole("STUDENT")
            	    .anyRequest().authenticated()
            	

            )
            //.formLogin(Customizer.withDefaults()) // use default login form
            .formLogin(form -> form
            	    .loginPage("/login") // your custom login controller
            	    .loginProcessingUrl("/login") // the POST URL from the form
            	    .defaultSuccessUrl("/default", true)
            	    .permitAll()
            	)
            
            .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout"));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
