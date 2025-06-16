package com.project.controller;

import com.project.model.Application;
import com.project.model.Student;
import com.project.service.StudentService;
import com.project.service.SubmittedApplicationService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SubmittedApplicationController {

    @Autowired
    private SubmittedApplicationService submittedApplicationService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/my-applications")
    public String viewApplications(Model model, HttpSession session) {
        // Get the student's email from session
        Student loggedInStudent = (Student) session.getAttribute("loggedInStudent");

        if (loggedInStudent == null) {
            return "redirect:/login";  // Redirect to login if no student is logged in
        }

        // Get the student from the database using the email from the session
        Student student = studentService.findByEmail(loggedInStudent.getEmail());

        if (student == null) {
            return "error";  // Handle error case (if student not found in DB)
        }

        // Fetch applications submitted by the student
        List<Application> applications = submittedApplicationService.getApplicationsByStudent(student);

        model.addAttribute("applications", applications);
        return "submitted-applications";  // View to show student's applications
    }
}
