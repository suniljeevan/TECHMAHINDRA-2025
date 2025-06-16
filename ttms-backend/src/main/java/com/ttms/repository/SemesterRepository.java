package com.ttms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttms.model.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Integer> {
	
}