package com.ttms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttms.model.Course;
import com.ttms.service.CourseService;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseRESTController {
	
	@Autowired
	private CourseService courseService;
	
	@PostMapping("/create-course")
	public ResponseEntity<String> createCourse(@RequestBody Course course) {
		System.out.println("Received Course: "+course);
		String status= courseService.upsert(course);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	@GetMapping("/{cid}")
    public ResponseEntity<Course> getAdminById(@PathVariable Integer cid) {
		Course course = courseService.getById(cid);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
	
	@GetMapping("/all-courses")
	public ResponseEntity<List<Course>> getAllCourses() {
		List<Course> allCourses= courseService.getAllCourses();
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }
	
	@GetMapping
	public ResponseEntity<List<Course>> handleDefaultCourseGet() {
        return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
	}
	
	@PutMapping("/update-course")
	public ResponseEntity<String> updateSemester(@RequestBody Course course) {
		String status= courseService.upsert(course);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{cid}")
	public ResponseEntity<String> deleteCourse(@PathVariable Integer cid) {
		String status= courseService.deleteById(cid);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
}