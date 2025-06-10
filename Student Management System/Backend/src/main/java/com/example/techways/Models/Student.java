package com.example.techways.Models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "student")
public class Student implements UserDetails {
    // name, email, password, phone, gender, rollNumber, department, program, batch,
    // role

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String gender;
    private String rollNumber;
    private String department;
    private String program;
    private String batch;
    private String role;

    @Override
    public String getUsername() {
        return email; // Assuming email is used as the username
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role); // Assuming role is a single authority
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Default implementation
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Default implementation
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Default implementation
    }

    @Override
    public boolean isEnabled() {
        return true; // Default implementation
    }
}
