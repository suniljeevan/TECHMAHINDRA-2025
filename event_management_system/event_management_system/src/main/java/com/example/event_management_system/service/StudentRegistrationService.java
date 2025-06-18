package com.example.event_management_system.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.event_management_system.model.StudentRegistration;
import com.example.event_management_system.repository.StudentRegistrationRepository;

@Service
public class StudentRegistrationService {

    @Autowired
    private StudentRegistrationRepository repository;

    public StudentRegistration registerStudent(StudentRegistration student) {
        return repository.save(student);
    }

    public boolean validateLogin(String registrationId, String password) {
        return repository.findByRegistrationIdAndPassword(registrationId, password) != null;
    }

    public boolean existsByRegistrationId(String registrationId) {
        return repository.existsById(registrationId);
    }

    public List<StudentRegistration> getAllStudents() {
        return repository.findAll();
    }

    public Optional<StudentRegistration> getStudentById(String registrationId) {
        return repository.findById(registrationId);
    }

    public StudentRegistration updateStudent(String registrationId, StudentRegistration updatedStudent) {
        if (repository.existsById(registrationId)) {
            updatedStudent.setRegistrationId(registrationId); // Ensure ID stays same
            return repository.save(updatedStudent);
        }
        return null;
    }

    public boolean deleteStudent(String registrationId) {
        if (repository.existsById(registrationId)) {
            repository.deleteById(registrationId);
            return true;
        }
        return false;
    }

    
}
