package com.university.result_management.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.result_management.models.Course;
import com.university.result_management.models.CourseEnrollment;
import com.university.result_management.models.Student;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {
    List<CourseEnrollment> findByStudentId(Long studentId);
    
    List<CourseEnrollment> findByCourseId(Long courseId);
    
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
    
    Optional<CourseEnrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);
    List<CourseEnrollment> findByStudent_IdAndCourse_Id(Long studentId, Long courseId);

    Optional<CourseEnrollment> findByStudentAndCourse(Student student, Course course);
    
    boolean existsByStudentIdAndCourseIdAndStatus(Long studentId, Long courseId, String status);
    
    Optional<CourseEnrollment> findTopByStudentIdAndCourseIdAndStatusOrderByAttemptNumberDesc(Long studentId, Long courseId, String status);


   

}