package com.ams.CONTROLLER;

import com.ams.MODEL.*;
import com.ams.SERVICE.*;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;


@Controller
public class MainController {

    private final StudentService studentService;
	private final UserService userService;

    public MainController(StudentService service, UserService userService) {
        this.studentService = service;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/student-register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-register";
    }

    @PostMapping("/student-register")
    public String registerStudent(@ModelAttribute Student student) {
        // Save student info to student table
        studentService.save(student);

        // Save login info to user table with role STUDENT
        User user = new User();
        user.setEmail(student.getEmail());
        //user.setUsername(student.getUsername()); // assuming student.username is used for login
        user.setPassword(student.getPassword()); // will be encoded in UserService
        user.setRole("STUDENT"); 

        userService.saveUser(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/admin-dashboard")
    
    public String adminDashboard(@RequestParam(required = false) String school, Model model) {
        List<Student> students = (school == null || school.isEmpty())
                ? studentService.findAll()
                : studentService.findBySchool(school);

        // Filtered lists for Thymeleaf (to avoid advanced filtering in the HTML)
        List<Student> pendingStudents = students.stream().filter(s -> "Pending".equalsIgnoreCase(s.getStatus())).toList();
        List<Student> approvedStudents = students.stream().filter(s -> "Approved".equalsIgnoreCase(s.getStatus())).toList();
        List<Student> declinedStudents = students.stream().filter(s -> "Declined".equalsIgnoreCase(s.getStatus())).toList();

        model.addAttribute("pendingStudents", pendingStudents);
        model.addAttribute("approvedStudents", approvedStudents);
        model.addAttribute("declinedStudents", declinedStudents);
        model.addAttribute("schoolList", studentService.getAllSchools());
        model.addAttribute("selectedSchool", school != null ? school : "All Schools");

        return "admin-dashboard";
    }

   

    @PostMapping("/update-status")
    public String updateStatus(@RequestParam(name = "id") Long id,
                               @RequestParam(name = "status") String status,
                               Model model) {
        studentService.updateStatus(id, status);
        model.addAttribute("students", studentService.findAll());
        return "redirect:/admin-dashboard";
    }
    
    @GetMapping("/dashboard")
    public String showStudentDashboard(Model model,
                                       @AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {

        Optional<Student> studentOpt = studentService.findByEmail(user.getUsername());
        if (studentOpt.isPresent()) {
            model.addAttribute("student", studentOpt.get());
            return "dashboard";
        }
        return "redirect:/login?error=studentNotFound";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }   
}
