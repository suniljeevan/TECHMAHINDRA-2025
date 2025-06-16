package com.ttms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttms.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}