package com.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.entity.Course;
import com.cms.entity.CustomUser;
import com.cms.entity.Enrollment;
import com.cms.repository.CourseRepository;
import com.cms.repository.EnrollmentRepository;
import com.cms.repository.UserRepository;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Enrollment enrollStudentToCourse(String studentId, String courseId) {
        CustomUser student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        
        if (!"STUDENT".equalsIgnoreCase(student.getRole())) {
            throw new RuntimeException("User is not a student");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getEnrollmentsByStudent(String studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(String courseId) {
        return enrollmentRepository.findByCourseCourseId(courseId);
    }

}
