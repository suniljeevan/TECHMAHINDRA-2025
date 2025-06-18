package com.ums.service;

import com.ums.model.Admin;
import com.ums.model.Alumni;
import com.ums.repository.AdminRepository;
import com.ums.repository.AlumniRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AlumniRepository alumniRepository;

    @Override
    public UserDetails loadUserByUsername(String input) {
        // 1. Try to find Admin by username
        Optional<Admin> adminOpt = adminRepository.findByUsername(input);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            return new org.springframework.security.core.userdetails.User(
                admin.getUsername(),
            
                admin.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        }

        // 2. Try to find Alumni by email or username
        Optional<Alumni> alumniOpt = alumniRepository.findByEmailOrUsername(input, input);
        if (alumniOpt.isPresent()) {
            Alumni alumni = alumniOpt.get();
            return new org.springframework.security.core.userdetails.User(
                alumni.getUsername(), // or alumni.getUsername()
                //alumni.getUsername(),
                alumni.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ALUMNI"))
            );
        }

        throw new UsernameNotFoundException("No user found with username or email: " + input);
    }
}
