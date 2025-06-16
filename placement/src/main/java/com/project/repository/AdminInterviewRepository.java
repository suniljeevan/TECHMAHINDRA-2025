package com.project.repository;

import com.project.model.Interview;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminInterviewRepository extends JpaRepository<Interview, Long> {
	// Custom query to find all scheduled interviews
    List<Interview> findByInterviewStatus(String status);
}
