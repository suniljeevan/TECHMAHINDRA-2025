package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Application;
import com.project.model.Student;

public interface SubmittedApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudent(Student student);
}
