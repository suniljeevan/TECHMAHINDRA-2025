package com.example.techways.Service;

import com.example.techways.DTO.CourseDTO;
import com.example.techways.DTO.RequestResponse;
import com.example.techways.Models.Course;
import com.example.techways.Repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Add a new course
    public RequestResponse addCourse(CourseDTO dto) {
        RequestResponse response = new RequestResponse();
        try {
            Course course = new Course();
            course.setCourseName(dto.getName());
            course.setCourseCode(dto.getCode());
            course.setCourseDescription(dto.getDescription());
            course.setCourseDuration(dto.getDuration());
            course.setCourseCredits(dto.getCredits());

            Course saved = courseRepository.save(course);
            response.setCourse(saved);
            response.setStatusCode(200);
            response.setMessage("Course added successfully");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Failed to add course: " + e.getMessage());
        }
        return response;
    }

    // Get all courses
    public RequestResponse getAllCourses() {
        RequestResponse response = new RequestResponse();
        try {
            List<Course> courses = courseRepository.findAll();
            if (!courses.isEmpty()) {
                response.setCourseList(courses);
                response.setStatusCode(200);
                response.setMessage("Courses fetched successfully");
            } else {
                response.setStatusCode(404);
                response.setMessage("No courses found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error fetching courses: " + e.getMessage());
        }
        return response;
    }

    // Get course by ID
    public RequestResponse getCourseById(Integer id) {
        RequestResponse response = new RequestResponse();
        try {
            Optional<Course> optional = courseRepository.findById(id);
            if (optional.isPresent()) {
                response.setCourse(optional.get());
                response.setStatusCode(200);
                response.setMessage("Course fetched successfully");
            } else {
                response.setStatusCode(404);
                response.setMessage("Course not found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error fetching course: " + e.getMessage());
        }
        return response;
    }

    // Update course
    public RequestResponse updateCourse(Integer id, CourseDTO dto) {
        RequestResponse response = new RequestResponse();
        try {
            Optional<Course> optional = courseRepository.findById(id);
            if (optional.isPresent()) {
                Course course = optional.get();
                course.setCourseName(dto.getName());
                course.setCourseCode(dto.getCode());
                course.setCourseDescription(dto.getDescription());
                course.setCourseDuration(dto.getDuration());
                course.setCourseCredits(dto.getCredits());

                Course updated = courseRepository.save(course);
                response.setCourse(updated);
                response.setStatusCode(200);
                response.setMessage("Course updated successfully");
            } else {
                response.setStatusCode(404);
                response.setMessage("Course not found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Failed to update course: " + e.getMessage());
        }
        return response;
    }

    // Delete course
    public RequestResponse deleteCourse(Integer id) {
        RequestResponse response = new RequestResponse();
        try {
            if (courseRepository.existsById(id)) {
                courseRepository.deleteById(id);
                response.setStatusCode(200);
                response.setMessage("Course deleted successfully");
            } else {
                response.setStatusCode(404);
                response.setMessage("Course not found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error deleting course: " + e.getMessage());
        }
        return response;
    }
}
