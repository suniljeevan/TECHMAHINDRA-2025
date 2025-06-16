package com.ttms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
	        .csrf(csrf -> csrf.disable()) // Disable CSRF protection for stateless APIs
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/admin/login", "/admin/register","/admin/{aid}", "/admin",
	            		"/faculty/login", "/faculty/register", "/faculty/{facultyid}", "/faculty/all-faculty", "/faculty/update-faculty", "/faculty/delete/{facultyid}", "/faculty",
	            		"/course/create-course", "/course/{cid}", "/course/all-courses", "/course", "/course/update-course", "/course/delete/{cid}",
	            		"/timetable/create-timetable", "/timetable/{timetableid}", "/timetable/get-timetable/{semName}/{sectionName}", "/timetable/all-timetables", "/timetable", "/timetable/update-timetable", "/timetable/delete/{timetableid}",
	            		"/student/login", "/student/register", "/student/{studentid}", "/student/all-students", "/student", "/student/update-student", "/student/delete/{studentid}",
	            		"/semester/create-semester", "/semester/create-semester", "/semester/{sid}", "/semester/all-semesters", "/semester",  "/semester/update-semester", "/semester/delete/{sid}",
	            		"/section/create-section", "/section/{secid}","/section/all-sections", "/section", "/section/update-section", "/section/delete/{secid}").permitAll()
	            .anyRequest().authenticated() // Require authentication for other requests
	        )
	        .sessionManagement(session -> session.disable()) // Disable session management (stateless)
	        .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class); // Add custom JWT filter

	    return http.build();
	}


}