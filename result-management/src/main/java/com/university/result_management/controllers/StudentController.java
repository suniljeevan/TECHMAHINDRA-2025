package com.university.result_management.controllers;


import java.io.ByteArrayInputStream;
import java.security.Principal;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.university.result_management.models.Course;
import com.university.result_management.models.CourseEnrollment;
import com.university.result_management.models.Result;
import com.university.result_management.models.Student;
import com.university.result_management.services.CourseEnrollmentService;
import com.university.result_management.services.CourseService;
import com.university.result_management.services.PdfGeneratorService;
import com.university.result_management.services.ResultService;
import com.university.result_management.services.StudentService;
import com.university.result_management.services.SystemSettingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final CourseEnrollmentService courseEnrollmentService;
    private final CourseService courseService;
    private final SystemSettingService settingService;
    private final ResultService resultService;
    private final PdfGeneratorService pdfGeneratorService;
 

    // ✅ Auto-add studentId to every request under /student
    @ModelAttribute
    public void addStudentIdToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String rollno = authentication.getName();  // get logged-in user's email
        Student student = studentService.findByRollno(rollno);
        model.addAttribute("studentId", student.getId());
        model.addAttribute("student",student);
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "html/student/student-dashboard";
    }
 // View Profile
    @GetMapping("/profile")
    public String getStudentProfile(Model model) {
        Long studentId = (Long) model.getAttribute("studentId");
        Student student = studentService.getStudentProfile(studentId);
        model.addAttribute("student", student);
        return "html/student/student-profile"; 
    }

    // View Enrollments
    @GetMapping("/enrollments")
    public String viewStudentEnrollments(Model model, Principal principal) {
        String rollno = principal.getName();
        Student student = studentService.findByRollno(rollno);

        // Get all course enrollments for the student
        List<CourseEnrollment> enrollments = courseEnrollmentService.getEnrollmentsByStudent(student.getId());

        model.addAttribute("enrollments", enrollments);
        return "html/student/student-enrollments";  // Thymeleaf view for displaying enrollments
    }
    @GetMapping("/courses")
    public String viewAvailableCourses(Model model) {
        List<Course> courses = courseService.getAllCourse();
        model.addAttribute("courses", courses);
        return "html/student/student-new-enrollment"; 
    }

 // Show available courses for enrollment
    @PostMapping("/enroll")
    public String enrollInCourse(@RequestParam("courseId") Long courseId, Model model) {
        if (!settingService.getSettings().isEnrollCourseEnabled()) {
            model.addAttribute("title", "Enrollment Disabled");
            model.addAttribute("message", "Course enrollment is currently disabled by the administrator.");
            model.addAttribute("backUrl", "/student/courses");
            return "html/common/message";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Student student = studentService.findByRollno(email);

        // Prevent enrollment if an attempt with null status exists
        if (courseEnrollmentService.hasIncompleteEnrollment(student.getId(), courseId)) {
            model.addAttribute("title", "Enrollment Not Allowed");
            model.addAttribute("message", "You have already enrolled in this course and are awaiting results.");
            model.addAttribute("backUrl", "/student/courses");
            return "html/common/message";
        }

        // Check if already passed (status = "Pass")
        if (courseEnrollmentService.isEnrolledAndPassed(student.getId(), courseId)) {
            model.addAttribute("title", "Already Passed");
            model.addAttribute("message", "You have already passed this course.");
            model.addAttribute("backUrl", "/student/courses");
            return "html/common/message";
        }

        // Allow re-enrollment only if previously failed
        if (courseEnrollmentService.isStudentAlreadyEnrolled(student.getId(), courseId)) {
            boolean failedBefore = courseEnrollmentService.hasFailedCourse(student.getId(), courseId);
            if (!failedBefore) {
                model.addAttribute("title", "Already Enrolled");
                model.addAttribute("message", "You have already enrolled in or passed this course.");
                model.addAttribute("backUrl", "/student/courses");
                return "html/common/message";
            }
        }

        courseEnrollmentService.enrollStudent(student.getId(), courseId);

        model.addAttribute("title", "Enrollment Successful");
        model.addAttribute("message", "You have successfully enrolled in the course.");
        model.addAttribute("backUrl", "/student/enrollments");
        return "html/common/message";
    }






    @GetMapping("/results")
    public String showSemesterSelectionPage() {
        return "html/student/student-select-semester";
    }

    // Show results for selected semester
    @PostMapping("/results")
    public String viewResultsBySemester(@RequestParam("semester") int semester,
                                        Principal principal,
                                        Model model) {
        String rollno = principal.getName();
        Student student = studentService.findByRollno(rollno);

        // Get results for selected semester
        List<Result> results = resultService.getResultsByStudentIdAndSemester(student.getId(), semester);
        model.addAttribute("results", results);
        model.addAttribute("selectedSemester", semester);

        // Get all enrollments for the student in that semester to display course names
        List<CourseEnrollment> enrollments = courseEnrollmentService.getEnrollmentsByStudentAndSemester(student.getId(), semester);
        model.addAttribute("enrollments", enrollments);

        // Calculate CGPA
        List<Result> allResults = resultService.getResultsByStudentId(student.getId());
        double cgpa = resultService.calculateCGPA(allResults);
        model.addAttribute("cgpa", cgpa);
        
        Integer maxCredits = resultService.getMaxTotalCredits(student.getId());
        model.addAttribute("totalCredits", maxCredits);

        return "html/student/student-result";
    }
    @GetMapping("/results/pdf")
    public ResponseEntity<InputStreamResource> downloadGradeCard(@RequestParam int semester, Principal principal) throws Exception {
        Student student = studentService.findByRollno(principal.getName());
        List<Result> results = resultService.getResultsByStudentIdAndSemester(student.getId(), semester);

        double totalGradePoints = 0;
        int totalCredits = 0;

        for (Result result : results) {
            if ("Pass".equalsIgnoreCase(result.getRemarks())) {
                double marks = result.getMarks();
                double gpa = resultService.calculateGPA(marks);
                int credits = result.getCourse().getCredits();

                totalGradePoints += gpa * credits;
                totalCredits += credits;
            }
        }


        double sgpa = totalCredits > 0 ? Math.round((totalGradePoints / totalCredits) * 100.0) / 100.0 : 0.0;

        List<Result> allResults = resultService.getResultsByStudentId(student.getId());
        double cgpa = resultService.calculateCGPA(allResults);
        int credits = resultService.getMaxTotalCredits(student.getId());

        ByteArrayInputStream pdfStream = pdfGeneratorService.generateGradeCard(student, results, semester, sgpa, cgpa, credits);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=grade_card_semester_" + semester + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }




}
