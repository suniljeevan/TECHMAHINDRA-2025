package com.org.TMS.controller;

import com.org.TMS.model.Student;
import com.org.TMS.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/registerStudent")
public class RegisterStudentController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterStudentController.class);

    private final RegisterService registerService;

    @Autowired
    public RegisterStudentController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("student", new Student());
        return "registerStudent";
    }

    @PostMapping
    public String registerStudent(@ModelAttribute Student student, Model model) {
        logger.info("Received student data for registration: {}", student);

        if (registerService.isStudentRegistered(student.getSid())) {
            model.addAttribute("message", "Student with ID " + student.getSid() + " is already registered.");
            logger.info("Student with SID {} is already registered.", student.getSid());
        } else {
            try {
                registerService.saveStudent(student);
                model.addAttribute("message", "Student registered successfully.");
                logger.info("Student registered successfully: {}", student);
            } catch (Exception e) {
                model.addAttribute("message", "An error occurred during registration. Please try again.");
                logger.error("Error during registration of student: {}", student, e);
            }
        }

        model.addAttribute("student", new Student()); // Reset form
        return "registerStudent";
    }
}
