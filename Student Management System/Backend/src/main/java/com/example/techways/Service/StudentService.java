package com.example.techways.Service;

import com.example.techways.DTO.StudentDTO;
import com.example.techways.Models.Student;
import com.example.techways.Repository.StudentRepository;
import com.example.techways.DTO.RequestResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import org.springframework.security.core.AuthenticationException;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Register a new student
    public RequestResponse registerStudent(StudentDTO dto) {
        RequestResponse response = new RequestResponse();
        try {
            Student student = new Student();
            student.setName(dto.getName());
            student.setEmail(dto.getEmail());
            student.setPhone(dto.getPhone());
            student.setGender(dto.getGender());
            student.setPassword(passwordEncoder.encode(dto.getPassword()));
            student.setDepartment(dto.getDepartment());
            student.setRollNumber(dto.getRollNumber());
            student.setProgram(dto.getProgram());
            student.setBatch(dto.getBatch());
            student.setRole(dto.getRole());

            Student saved = studentRepository.save(student);

            if (saved.getId() > 0) {
                response.setStudent(saved);
                response.setMessage("Student registered successfully");
                response.setStatusCode(200);
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Failed to register student: " + e.getMessage());
        }
        return response;
    }

    // Student login
    public RequestResponse loginStudent(String email, String password) {
        RequestResponse response = new RequestResponse();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            Student student = studentRepository.findByEmail(email).orElseThrow();
            String token = jwtUtils.generateToken(student);
            String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), student);

            response.setStatusCode(200);
            response.setToken(token);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setRole(student.getRole());
            response.setMessage("Student login successful");
        } catch (AuthenticationException e) {
            response.setStatusCode(401);
            response.setMessage("Login failed: " + e.getMessage());
        }
        return response;
    }

    // Refresh JWT token
    public RequestResponse refreshToken(RequestResponse request) {
        RequestResponse response = new RequestResponse();
        try {
            String email = jwtUtils.extractUsername(request.getToken());
            Student student = studentRepository.findByEmail(email).orElseThrow();
            if (jwtUtils.isTokenValid(request.getToken(), student)) {
                String newToken = jwtUtils.generateToken(student);
                response.setToken(newToken);
                response.setRefreshToken(request.getToken());
                response.setExpirationTime("24Hr");
                response.setStatusCode(200);
                response.setMessage("Token refreshed successfully");
            } else {
                response.setStatusCode(403);
                response.setMessage("Invalid token");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error: " + e.getMessage());
        }
        return response;
    }

    // Get all students
    public RequestResponse getAllStudents() {
        RequestResponse response = new RequestResponse();
        try {
            List<Student> students = studentRepository.findAll();
            if (!students.isEmpty()) {
                response.setStudentList(students);
                response.setStatusCode(200);
                response.setMessage("Students fetched successfully");
            } else {
                response.setStatusCode(404);
                response.setMessage("No students found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error: " + e.getMessage());
        }
        return response;
    }

    // Get student by ID
    public RequestResponse getStudentById(Integer id) {
        RequestResponse response = new RequestResponse();
        try {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            response.setStudent(student);
            response.setStatusCode(200);
            response.setMessage("Student found successfully");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error: " + e.getMessage());
        }
        return response;
    }

    // Update student
    public RequestResponse updateStudent(Integer id, StudentDTO dto) {
        RequestResponse response = new RequestResponse();
        try {
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                student.setName(dto.getName());
                student.setEmail(dto.getEmail());
                student.setPhone(dto.getPhone());
                student.setGender(dto.getGender());
                student.setDepartment(dto.getDepartment());
                student.setProgram(dto.getProgram());
                student.setBatch(dto.getBatch());
                student.setRollNumber(dto.getRollNumber());
                student.setRole(dto.getRole());

                Student updated = studentRepository.save(student);
                response.setStudent(updated);
                response.setStatusCode(200);
                response.setMessage("Student updated successfully");
            } else {
                response.setStatusCode(404);
                response.setMessage("Student not found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Update failed: " + e.getMessage());
        }
        return response;
    }

    // Delete student
    public RequestResponse deleteStudent(Integer id) {
        RequestResponse response = new RequestResponse();
        try {
            if (studentRepository.existsById(id)) {
                studentRepository.deleteById(id);
                response.setStatusCode(200);
                response.setMessage("Student deleted successfully");
            } else {
                response.setStatusCode(404);
                response.setMessage("Student not found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error while deleting: " + e.getMessage());
        }
        return response;
    }

    // Get student info by email
    public RequestResponse getStudentInfo(String email) {
        RequestResponse response = new RequestResponse();
        try {
            Optional<Student> studentOpt = studentRepository.findByEmail(email);
            if (studentOpt.isPresent()) {
                response.setStudent(studentOpt.get());
                response.setStatusCode(200);
                response.setMessage("Student info fetched");
            } else {
                response.setStatusCode(404);
                response.setMessage("Student not found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error: " + e.getMessage());
        }
        return response;
    }
}