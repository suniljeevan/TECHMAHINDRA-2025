package com.admin.scholarship.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf().disable()
	        .authorizeHttpRequests()
	            .requestMatchers("/", "/login", "/login-handler", "/css/**", "/js/**", "/images/**").permitAll()
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .requestMatchers("/student/**").hasRole("STUDENT")
	            .requestMatchers("/admin/searchStudent").hasAnyRole("ADMIN", "STUDENT") 
	            .anyRequest().authenticated()
	        .and()
	        .formLogin()
	            .loginPage("/login")
	            .loginProcessingUrl("/login-handler")
	            .successHandler(customAuthSuccessHandler()) // 🔁
	            .permitAll()
	        .and()
	        .logout()
	            .logoutSuccessUrl("/login?logout")
	            .permitAll();

	    return http.build();
	}


    //handler for role-based redirection
    @Bean
    public AuthenticationSuccessHandler customAuthSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Authentication authentication)
                    throws IOException, ServletException {
                if (authentication.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                    response.sendRedirect("/admin/dashboard");
                } else if (authentication.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_STUDENT"))) {
                    response.sendRedirect("/student/home");
                } else {
                    response.sendRedirect("/login?error");
                }
            }
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin123")
            .roles("ADMIN")
            .build();

        UserDetails student = User.withDefaultPasswordEncoder()
            .username("student")
            .password("student123")
            .roles("STUDENT")
            .build();

        return new InMemoryUserDetailsManager(admin, student);
    }
    
    protected void configure(HttpSecurity http) throws Exception {
        http
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login") // redirects to login.html
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    
}
