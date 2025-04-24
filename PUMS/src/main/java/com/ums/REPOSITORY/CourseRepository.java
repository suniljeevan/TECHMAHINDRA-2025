package com.ums.REPOSITORY;

import com.ums.MODEL.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}
