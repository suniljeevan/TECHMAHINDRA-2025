package com.university.result_management.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.university.result_management.models.Admin;
import com.university.result_management.models.Instructor;
import com.university.result_management.models.Student;
import com.university.result_management.repositories.AdminRepository;
import com.university.result_management.repositories.InstructorRepository;
import com.university.result_management.repositories.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepo;
    private final StudentRepository studentRepo;
    private final InstructorRepository instructorRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Admin> adminOpt = adminRepo.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            return User.builder()
                    .username(admin.getUsername())
                    .password(admin.getPassword())
                    .roles("ADMIN")
                    .build();
        }

        Optional<Student> studentOpt = studentRepo.findByRollNumber(username);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            return User.builder()
                    .username(student.getRollNumber())
                    .password(student.getPassword())
                    .roles("STUDENT")
                    .build();
        }

        Optional<Instructor> instructorOpt = instructorRepo.findByEmail(username);
        if (instructorOpt.isPresent()) {
            Instructor instructor = instructorOpt.get();
            return User.builder()
                    .username(instructor.getEmail())
                    .password(instructor.getPassword())
                    .roles("INSTRUCTOR")
                    .build();
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
