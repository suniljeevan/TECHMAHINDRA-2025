package com.project.repository;

import com.project.model.Application; 
import com.project.model.Job;
import com.project.model.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // Find an existing application by student and job
	Application findByJobAndStudent(Job job, Student student);
	

}
