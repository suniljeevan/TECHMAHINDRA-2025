package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Interview;

public interface StudentInterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByApplication_Student_Id(Long studentId);
}
