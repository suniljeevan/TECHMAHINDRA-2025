package com.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.entity.Course;
import com.cms.repository.CourseRepository;
import com.cms.repository.EnrollmentRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
    private EnrollmentRepository enrollmentRepository;
	
	// Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get course by ID
    public Optional<Course> getCourseById(String courseId) {
        return courseRepository.findById(courseId);
    }

    // Add or update a course
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    // Delete a course
    @Transactional
    public void deleteCourse(String courseId) {
    	enrollmentRepository.deleteByCourse_CourseId(courseId);
        courseRepository.deleteById(courseId);
    }

    // Find by courseId
    public List<Course> findByCourseId(String courseId) {
        return courseRepository.findByCourseId(courseId);
    }

    public Course updateCourse(String courseId, Course updatedCourse) {
        Optional<Course> existingCourseOpt = courseRepository.findById(courseId);
        
        if (existingCourseOpt.isPresent()) {
            Course existingCourse = existingCourseOpt.get();
            existingCourse.setCourseName(updatedCourse.getCourseName());
            existingCourse.setCourseDescription(updatedCourse.getCourseDescription());
            existingCourse.setCourseCredits(updatedCourse.getCourseCredits());
            existingCourse.setCourseType(updatedCourse.getCourseType());
            
            return courseRepository.save(existingCourse);
        } else {
            throw new RuntimeException("Course not found with ID: " + courseId);
        }
    }
}
