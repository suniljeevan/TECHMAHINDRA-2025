package com.university.result_management.controllers;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.university.result_management.models.Course;
import com.university.result_management.models.CourseEnrollment;
import com.university.result_management.models.Instructor;
import com.university.result_management.services.CourseEnrollmentService;
import com.university.result_management.services.CourseService;
import com.university.result_management.services.InstructorService;
import com.university.result_management.services.MarksAssignmentService;
import com.university.result_management.services.SystemSettingService;
import com.university.result_management.services.UploadService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;
    private final CourseEnrollmentService courseEnrollService;
    private final MarksAssignmentService marksAssignmentService;
    private final UploadService uploadService;
    private final SystemSettingService settingService;
    private final CourseService courseService;
    // Instructor Dashboard
    // ✅ This method runs before every request and adds instructorId to the model
    @ModelAttribute
    public void addInstructorIdToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Instructor instructor = instructorService.findByEmail(email);
        model.addAttribute("instructorId", instructor.getId());
        model.addAttribute("instructor", instructor);
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "html/instructor/instructor-dashboard";
    }
    @GetMapping("/profile")
    public String instructorProfile() {
        return "html/instructor/instructor-profile";
    }

    // Show courses assigned to instructor
    @GetMapping("/courses")
    public String viewCourses(@RequestParam("instructorId") Long instructorId, Model model) {
        List<Course> courses = courseService.getCoursesByInstructorId(instructorId);
        model.addAttribute("courses", courses);
        return "html/instructor/instructor-courses"; // your courses list page
    }

    // View enrollments for a specific course
    @GetMapping("/course/{courseId}/students")
    public String viewStudentsInCourse(@PathVariable Long courseId, Model model) {
        List<CourseEnrollment> enrollments = courseEnrollService.getEnrollmentsByCourse(courseId);
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("courseId", courseId);
        return "html/instructor/course_students"; // page where you show students and allow marks entry
    }
    @GetMapping("/courses/{courseId}/upload-marks")
    public String showUploadMarksPage(@PathVariable Long courseId, Model model) {
        model.addAttribute("courseId", courseId);
        return "/html/instructor/upload-marks"; // points to templates/instructor/upload-marks.html
    }


    @PostMapping("/course/{courseId}/upload-marks-confirm")
    public String previewMarksUpload(@PathVariable Long courseId,
                                     @RequestParam("file") MultipartFile file,
                                     HttpSession session,
                                     Model model) throws IOException {

        List<Map<String, Object>> previewList = uploadService.parseMarksToMap(file);
        session.setAttribute("previewData", previewList); // store temporarily for final submission
        session.setAttribute("uploadedCourseId", courseId);

        model.addAttribute("previewList", previewList);
        model.addAttribute("courseId", courseId);
        return "html/instructor/marks-upload-preview"; // confirmation page
    }

    @PostMapping("/course/{courseId}/upload-marks-final")
    public String finalizeMarksUpload(HttpSession session, Model model) {
        Long courseId = (Long) session.getAttribute("uploadedCourseId");
        @SuppressWarnings("unchecked")
		List<Map<String, Object>> data = (List<Map<String, Object>>) session.getAttribute("previewData");

        if (data != null && courseId != null) {
            uploadService.saveUploadedMarks(courseId, data);
            session.removeAttribute("previewData");
            session.removeAttribute("uploadedCourseId");

            model.addAttribute("title", "Success");
            model.addAttribute("message", "Marks uploaded and saved successfully.");
            model.addAttribute("backUrl", "/instructor/course/" + courseId + "/students");
            return "html/common/message";
        } else {
            model.addAttribute("title", "Error");
            model.addAttribute("message", "No uploaded data found.");
            model.addAttribute("backUrl", "/instructor");
            return "html/common/message";
        }
    }



    // Submit marks for students
    @PostMapping("/course/{courseId}/assign-ca-marks")
    public String assignCaMarks(@PathVariable Long courseId,
            @RequestParam("enrollmentIds") List<Long> enrollmentIds,
            @RequestParam("marks") List<Double> marks,
            Model model) {

        // Validate the marks before passing them to the service
        for (Double mark : marks) {
            if (mark == null || mark.isNaN()) {
                model.addAttribute("title", "Error");
                model.addAttribute("message", "Invalid marks input detected.");
                model.addAttribute("backUrl", "/instructor/course/" + courseId + "/students");
                return "html/common/message";
            }
        }

        // Pass the `Model` object to the service method
        String resultPage = marksAssignmentService.assignCAMarksBulk(enrollmentIds, marks, model);

        return resultPage; // Return the appropriate page based on the service response
    }
    @PostMapping("/course/{courseId}/assign-midterm-marks")
    public String assignMidtermMarks(@PathVariable Long courseId,
            @RequestParam("enrollmentIds") List<Long> enrollmentIds,
            @RequestParam("marks") List<Double> marks,
            Model model) {

        // Validate the marks before passing them to the service
        for (Double mark : marks) {
            if (mark == null || mark.isNaN()) {
                model.addAttribute("title", "Error");
                model.addAttribute("message", "Invalid marks input detected.");
                model.addAttribute("backUrl", "/instructor/course/" + courseId + "/students");
                return "html/common/message";
            }
        }

        // Pass the `Model` object to the service method
        String resultPage = marksAssignmentService.assignMidtermMarksBulk(enrollmentIds, marks, model);

        return resultPage; // Return the appropriate page based on the service response
    }
    @PostMapping("/course/{courseId}/assign-endterm-marks")
    public String assignEndtermMarks(@PathVariable Long courseId,
            @RequestParam("enrollmentIds") List<Long> enrollmentIds,
            @RequestParam("marks") List<Double> marks,
            Model model) {

        // Validate the marks before passing them to the service
        for (Double mark : marks) {
            if (mark == null || mark.isNaN()) {
                model.addAttribute("title", "Error");
                model.addAttribute("message", "Invalid marks input detected.");
                model.addAttribute("backUrl", "/instructor/course/" + courseId + "/students");
                return "html/common/message";
            }
        }

        // Pass the `Model` object to the service method
        String resultPage = marksAssignmentService.assignEndtermMarksBulk(enrollmentIds, marks, model);

        return resultPage; // Return the appropriate page based on the service response
    }


 // View all course marks for a specific student
    @GetMapping("/student/{studentId}/marks")
    public String viewStudentMarks(@PathVariable Long studentId, Model model) {
        List<CourseEnrollment> enrollments = courseEnrollService.getEnrollmentsByStudent(studentId);
        model.addAttribute("enrollments", enrollments);
        return "html/instructor/student-marks"; // You will create this view
    }
}
