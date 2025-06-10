package com.example.techways.Models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "faculty")
public class Faculty implements UserDetails {

    @Id
    @Column(name = "faculty_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facultyId;

    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;
    private String department;
    private String designation;

    // @ManyToOne
    // @JoinColumn(name = "admin_id", referencedColumnName = "admin_id")
    // private Admin admin;

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
