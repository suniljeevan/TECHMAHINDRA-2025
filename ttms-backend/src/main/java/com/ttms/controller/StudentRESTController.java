package com.ttms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttms.model.StudentUser;
import com.ttms.security.JwtUtil;
import com.ttms.service.StudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentRESTController {
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> loginStudentUser(@RequestBody StudentUser studentUser) {
        // Check if the student credentials are valid
        StudentUser existingStudent = studentService.findByEmailAndPassword(studentUser.getStudentEmail(), studentUser.getStudentPassword());

        if (existingStudent != null) {
        	String token = jwtUtil.generateToken(existingStudent.getStudentEmail());

            // Return the token in the response
            return ResponseEntity.ok("Bearer: " + token);  // Send the token with a "Bearer" prefix
        } else {
            // If invalid credentials, return error message
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    // Student Registration API (for new users)
    @PostMapping("/register")
    public ResponseEntity<String> createStudentUser(@RequestBody StudentUser studentUser) {
        String status = studentService.upsert(studentUser);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("/{studentid}")
    public ResponseEntity<StudentUser> getStudentById(@PathVariable Integer studentid) {
    	StudentUser studentUser = studentService.getStudentById(studentid);
        return new ResponseEntity<>(studentUser, HttpStatus.OK);
    }
    
    @GetMapping("/all-students")
	public ResponseEntity<List<StudentUser>> getAllStudent() {
		List<StudentUser> allStudents= studentService.getAllStudents();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }
	
	@GetMapping
	public ResponseEntity<List<StudentUser>> handleDefaultStudentGet() {
        return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
	}
    
    @PutMapping("/update-student")
	public ResponseEntity<String> updateStudent(@RequestBody StudentUser studentUser) {
		String status= studentService.upsert(studentUser);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{studentid}")
	public ResponseEntity<String> deleteStudent(@PathVariable Integer studentid) {
		String status= studentService.deleteById(studentid);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
}
