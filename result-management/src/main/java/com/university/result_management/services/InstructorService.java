package com.university.result_management.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.university.result_management.models.Course;


import com.university.result_management.models.Instructor;

import com.university.result_management.repositories.CourseRepository;
import com.university.result_management.repositories.InstructorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final CourseRepository courseRepo;

    private final InstructorRepository instructorRepo;
    
    public List<Instructor> getAllInstructor() {
		 
        return instructorRepo.findAll();
    }
    public Instructor getInstructorById(Long id) {
	    return instructorRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Instructor not found"));
	}
    public List<Instructor> getInstructorByDepartment(Long id) {
	    return instructorRepo. findByDepartmentId(id);

	}
    public Instructor findByEmail(String email) {
        return instructorRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));
    }

    public List<Course> getCoursesByInstructor(Long instructorId) {
        return courseRepo.findAll().stream()
                .filter(c -> c.getInstructor().getId().equals(instructorId))
                .toList();
    }

    
    
}
