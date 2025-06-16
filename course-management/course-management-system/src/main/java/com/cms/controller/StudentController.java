package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cms.entity.CustomUser;
import com.cms.repository.UserRepository;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/{id}")
    public CustomUser getStudentById(@PathVariable String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

}
