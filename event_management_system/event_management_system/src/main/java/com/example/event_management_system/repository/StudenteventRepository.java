package com.example.event_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.event_management_system.model.Student;
@Repository
public interface StudenteventRepository extends JpaRepository<Student, String> {
	
}