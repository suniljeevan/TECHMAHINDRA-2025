package com.university.result_management.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.result_management.models.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
	 Optional<Instructor> findByEmail(String email);
    List<Instructor> findByDepartmentId(Long departmentId);
    boolean existsByEmail(String email);
}
