package com.example.event_management_system.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.event_management_system.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByCollegeMailId(String collegeMailId);
}

