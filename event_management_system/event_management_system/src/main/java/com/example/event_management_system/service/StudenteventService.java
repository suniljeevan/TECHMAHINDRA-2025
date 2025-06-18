package com.example.event_management_system.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.event_management_system.model.Student;
import com.example.event_management_system.repository.StudenteventRepository;

@Service
public class StudenteventService {
	@Autowired
    private StudenteventRepository studentRepository;

    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(String registrationId) {
        return studentRepository.findById(registrationId);
    }

}
