package com.example.event_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.event_management_system.model.Student;
import com.example.event_management_system.service.StudenteventService;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class StudenteventController {
	@Autowired
    private StudenteventService studenteventService;

    @PostMapping("/event-registration")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studenteventService.registerStudent(student);
            return ResponseEntity.ok(savedStudent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
