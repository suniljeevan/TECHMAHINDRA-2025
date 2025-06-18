package com.example.event_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.event_management_system.model.StudentRegistration;

public interface StudentRegistrationRepository extends JpaRepository<StudentRegistration, String> {
    StudentRegistration findByRegistrationIdAndPassword(String registrationId, String password);
}
