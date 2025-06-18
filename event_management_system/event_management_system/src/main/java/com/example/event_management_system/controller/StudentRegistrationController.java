package com.example.event_management_system.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.event_management_system.model.StudentRegistration;
import com.example.event_management_system.service.StudentRegistrationService;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentRegistrationController {

    @Autowired
    private StudentRegistrationService service;

    // Signup handler
    @PostMapping("/signup")
    public String signup(@RequestParam String registrationId,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            return "Passwords do not match!";
        }

        if (service.existsByRegistrationId(registrationId)) {
            return "Registration ID already exists!";
        }

        StudentRegistration student = new StudentRegistration();
        student.setRegistrationId(registrationId);
        student.setEmail(email);
        student.setPassword(password);

        service.registerStudent(student);
        return "Registration Successful!";
    }

    // Login handler
    @PostMapping("/login")
    public String login(@RequestParam String registrationId, @RequestParam String password) {
        boolean isValid = service.validateLogin(registrationId, password);
        return isValid ? "Login Successful!" : "Invalid credentials!";
    }

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRegistration student) {
        if (service.existsByRegistrationId(student.getRegistrationId())) {
            return "Registration ID already exists!";
        }
        service.registerStudent(student);
        return "Student Added Successfully!";
    }

    // ✅ UPDATE student
    @PutMapping("/update/{registrationId}")
    public String updateStudent(@PathVariable String registrationId, @RequestBody StudentRegistration student) {
        if (!service.existsByRegistrationId(registrationId)) {
            return "Student not found!";
        }
        student.setRegistrationId(registrationId);
        service.registerStudent(student); // save() updates if ID exists
        return "Student Updated Successfully!";
    }

    // ✅ DELETE student
    @DeleteMapping("/delete/{registrationId}")
    public String deleteStudent(@PathVariable String registrationId) {
        if (!service.existsByRegistrationId(registrationId)) {
            return "Student not found!";
        }
        service.deleteStudent(registrationId);
        return "Student Deleted Successfully!";
    }

    // ✅ VIEW all students
    @GetMapping("/all")
    public List<StudentRegistration> getAllStudents() {
        return service.getAllStudents();
    }
}
