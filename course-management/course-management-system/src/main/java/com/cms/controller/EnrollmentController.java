package com.cms.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.cms.dto.CourseWithStudentsDTO;
import com.cms.dto.StudentDTO;
import com.cms.entity.Course;
import com.cms.entity.CustomUser;
import com.cms.entity.Enrollment;
import com.cms.repository.CourseRepository;
import com.cms.repository.EnrollmentRepository;
import com.cms.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
@CrossOrigin(origins = "http://localhost:3000")
public class EnrollmentController {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public Enrollment enrollStudent(@RequestParam String studentId,
                                    @RequestParam String courseId,
                                    Authentication authentication) {
        // Optional: Check if studentId matches authenticated user
        return enrollmentService.enrollStudentToCourse(studentId, courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<Course> getCoursesByStudent(@PathVariable String studentId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
        return enrollments.stream()
                .map(Enrollment::getCourse)
                .collect(Collectors.toList());
    }

    @GetMapping("/course/{courseId}")
    public List<String> getStudentsByCourse(@PathVariable String courseId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourse(courseId);
        return enrollments.stream()
                .map(e -> e.getStudent().getFirstName() + " " + e.getStudent().getLastName())
                .collect(Collectors.toList());
    }
    
    @Autowired
    EnrollmentRepository enrollmentRepository;
    
    @DeleteMapping("/unenroll")
    public ResponseEntity<Void> unenrollCourse(@RequestParam String studentId, @RequestParam String courseId) {
        enrollmentRepository.unenrollCourse(studentId, courseId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/check")
    public ResponseEntity<Map<String, Boolean>> checkEnrollment(
            @RequestParam String studentId,
            @RequestParam String courseId) {
        boolean isEnrolled = enrollmentRepository.existsByStudent_IdAndCourse_CourseId(studentId, courseId);
        System.out.println("isEnrolled: " +isEnrolled);
        return ResponseEntity.ok(Collections.singletonMap("isEnrolled", isEnrolled));
    }
    
    @Autowired
    CourseRepository courseRepository;
    
    @GetMapping("/courses/{courseId}/details")
    public ResponseEntity<CourseWithStudentsDTO> getCourseWithStudents(@PathVariable String courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found"));

        List<Enrollment> enrollments = enrollmentRepository.findByCourseCourseId(courseId);

        List<StudentDTO> students = enrollments.stream().map(enrollment -> {
            CustomUser user = enrollment.getStudent();
            StudentDTO dto = new StudentDTO();
            dto.setId(user.getId());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setEmail(user.getEmail());
            return dto;
        }).toList();

        CourseWithStudentsDTO courseDTO = new CourseWithStudentsDTO();
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCourseCredits(course.getCourseCredits());
        courseDTO.setCourseDescription(course.getCourseDescription());
        courseDTO.setCourseType(course.getCourseType());
        courseDTO.setEnrolledStudents(students);

        return ResponseEntity.ok(courseDTO);
    }


}
