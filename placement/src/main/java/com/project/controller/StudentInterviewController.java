package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.model.Interview;
import com.project.model.Student;
import com.project.service.StudentInterviewService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentInterviewController {

    @Autowired
    private StudentInterviewService interviewService;

    @GetMapping("/interviews")
    public String viewInterviews(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("loggedInStudent");

        if (student == null) {
            return "redirect:/login";
        }

        List<Interview> interviews = interviewService.getInterviewsByStudentId(student.getId());
        model.addAttribute("interviews", interviews);
        return "interview-list";
    }
}
