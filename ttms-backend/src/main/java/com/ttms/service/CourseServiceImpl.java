package com.ttms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttms.model.Course;
import com.ttms.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository courseRepo;
	
	public String upsert(Course course) {
		courseRepo.save(course);
		return "Success";
	}
	
	public Course getById(Integer cid) {
		Optional<Course> findById= courseRepo.findById(cid);
		
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
	
	public List<Course> getAllCourses() {
		return courseRepo.findAll();
	}
	
	public String deleteById(Integer cid) {
		if(courseRepo.existsById(cid)) {
			courseRepo.deleteById(cid);
			return "Deletion success";
		} else {
			return "No Data found";
		}
	}
}
