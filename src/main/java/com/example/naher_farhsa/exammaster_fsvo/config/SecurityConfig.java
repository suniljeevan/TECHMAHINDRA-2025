package com.example.naher_farhsa.exammaster_fsvo.config;


import com.example.naher_farhsa.exammaster_fsvo.Service.CustomUserDetailsService;
import com.example.naher_farhsa.exammaster_fsvo.Service.JwtService;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public SecurityConfig(CustomUserDetailsService uds, PasswordEncoder encoder,JwtService jwtService) {
        this.userDetailsService = uds;
        this.passwordEncoder = encoder;
        this.jwtService=jwtService;
    }
/*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/hallAllocation/**","/exam", "/exammaster/dashboard").hasRole("ADMIN")
                        .requestMatchers("exammaster/login", "/css/**", "/exammaster/home", "/auth/login","/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider());

        // Add JWT filter here if needed (e.g., jwtAuthFilter)

        return http.build();
    }*/

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/signup","/auth/login").permitAll()
                        .requestMatchers("/hallAllocation/**","/exam", "/exammaster/dashboard").hasRole("ADMIN")
                        .requestMatchers("/exammaster/studentDashboard").hasRole("USER")
                        .requestMatchers("/exammaster/login", "/css/**", "/exammaster/home", "/auth/**","/js/**","/logout").permitAll()

                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                //added after
                .logout(logout -> logout.disable());
        return http.build();
    }

}
