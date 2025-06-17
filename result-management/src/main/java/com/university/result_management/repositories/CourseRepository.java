package com.university.result_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.result_management.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	 List<Course> findByInstructorId(Long instructorId);
	 boolean existsByCode(String code);
}
