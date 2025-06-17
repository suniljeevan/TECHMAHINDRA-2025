package com.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.entity.Course;
import java.util.List;


public interface CourseRepository extends JpaRepository<Course, String> {
	List<Course> findByCourseId(String courseId);
}
