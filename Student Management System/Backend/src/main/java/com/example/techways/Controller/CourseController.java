package com.example.techways.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.techways.DTO.CourseDTO;
import com.example.techways.DTO.RequestResponse;
import com.example.techways.Service.CourseService;

@RestController
@RequestMapping("/course")
@CrossOrigin("*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Create a new course
    @PostMapping("/add")
    public RequestResponse addCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.addCourse(courseDTO);
    }

    // Get all courses
    @GetMapping("/all")
    public RequestResponse getAllCourses() {
        return courseService.getAllCourses();
    }

    // Get course by ID
    @GetMapping("/{id}")
    public RequestResponse getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    // Update course
    @PutMapping("/update/{id}")
    public RequestResponse updateCourse(@PathVariable Integer id, @RequestBody CourseDTO courseDTO) {
        return courseService.updateCourse(id, courseDTO);
    }

    // Delete course
    @DeleteMapping("/delete/{id}")
    public RequestResponse deleteCourse(@PathVariable Integer id) {
        return courseService.deleteCourse(id);
    }
}
