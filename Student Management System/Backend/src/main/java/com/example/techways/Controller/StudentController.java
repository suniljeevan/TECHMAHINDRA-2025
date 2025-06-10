package com.example.techways.Controller;

import com.example.techways.DTO.RequestResponse;
import com.example.techways.DTO.StudentDTO;
import com.example.techways.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Register Student
    @PostMapping("/register")
    public ResponseEntity<RequestResponse> registerStudent(@RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.registerStudent(dto));
    }

    // Student Login
    @PostMapping("/login")
    public ResponseEntity<RequestResponse> loginStudent(@RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.loginStudent(dto.getEmail(), dto.getPassword()));
    }

    // Refresh Token
    @PostMapping("/refresh")
    public ResponseEntity<RequestResponse> refreshToken(@RequestBody RequestResponse request) {
        return ResponseEntity.ok(studentService.refreshToken(request));
    }

    // Get All Students
    @GetMapping("/get-all")
    public ResponseEntity<RequestResponse> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // Get Student by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<RequestResponse> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // Update Student
    @PutMapping("/update/{id}")
    public ResponseEntity<RequestResponse> updateStudent(@PathVariable Integer id, @RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    // Delete Student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RequestResponse> deleteStudent(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }

    // Get My Info (authenticated student)
    @GetMapping("/get-my-info")
    public ResponseEntity<RequestResponse> getMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return ResponseEntity.ok(studentService.getStudentInfo(email));
    }
}