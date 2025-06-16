package com.project.controller;

import com.project.model.Student;
import com.project.model.Admin;
import com.project.service.StudentServiceImpl;
import com.project.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignUpController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private AdminServiceImpl adminService;

    // Show the signup role selection page
    @GetMapping("/signup")
    public String showSignUpSelection() {
        return "signup"; // templates/signup.html (select Student/Admin)
    }

    @GetMapping("/student-signup")
    public String showStudentSignUpForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-signup"; // Make sure the view is in templates/student-signup.html
    }

    @PostMapping("/student-signup")
    public String registerStudent(@ModelAttribute("student") Student student, Model model) {
        if (studentService.findByEmail(student.getEmail()) != null) {
            model.addAttribute("error", "Email already registered");
            return "student-signup"; // Redirect to sign-up page on error
        }
     // Set default CGPA if it's null (you can choose a default value or keep it null)
        if (student.getCgpa() == null) {
            student.setCgpa(null);  // You can set a default value if needed, like 0.0
        }
        
        student.setRole("STUDENT"); // Assign role 'STUDENT' here
        studentService.createStudent(student);
        return "redirect:/login"; // Redirect to login after successful signup
    }

    @GetMapping("/admin-signup")
    public String showAdminSignUpForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin-signup"; // Ensure this view exists in templates/admin-signup.html
    }

    @PostMapping("/admin-signup")
    public String registerAdmin(@ModelAttribute("admin") Admin admin, Model model) {
        if (adminService.findByEmail(admin.getEmail()) != null) {
            model.addAttribute("error", "Email already registered");
            return "admin-signup"; // Redirect to admin sign-up page on error
        }
        admin.setRole("ADMIN"); // Assign role 'ADMIN' here
        adminService.createAdmin(admin);
        return "redirect:/login"; // Redirect to login after successful signup
    }
}
