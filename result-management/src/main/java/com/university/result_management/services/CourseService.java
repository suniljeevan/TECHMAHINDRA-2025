package com.university.result_management.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.university.result_management.models.Course;

import com.university.result_management.repositories.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepo;
	
	 public Course getOneCourse(Long id) {
	        return courseRepo.findById(id).orElseThrow();
	    }

	
	  public List<Course> getAllCourse(){
	    	return courseRepo.findAll();
	    }
	public List<Course> getCoursesByInstructorId(Long id){
		return courseRepo.findByInstructorId(id);
	}

}
