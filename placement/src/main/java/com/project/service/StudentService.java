package com.project.service;

import com.project.model.Student;
import java.util.List;

public interface StudentService {

    Student createStudent(Student student);  // Method to create a new student
    Student findByEmail(String email);  // Method to find student by email
    List<Student> getAllStudents(); // Method to get all students

    // Add this method
    Student getStudentById(Long studentId);  // Method to find student by ID
}
