package com.project.service;

import com.project.model.Student;
import com.project.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);  // Save the student in the database
    }

    @Override
    public Student findByEmail(String email) {
        Optional<Student> studentOptional = studentRepository.findByEmail(email);  // Find student by email
        return studentOptional.orElse(null);  // Return the student or null if not found
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();  // Fetch all students from the repository
    }

    // Additional methods as needed
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    
    }

