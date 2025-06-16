package com.project.controller;

import jakarta.servlet.http.HttpSession;
import com.project.model.Student;
import com.project.model.Admin;
import com.project.service.StudentServiceImpl;
import com.project.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private AdminServiceImpl adminService;

    // Show role selection page
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // templates/login.html with role buttons
    }

    // Student Login Page
    @GetMapping("/student-login")
    public String showStudentLoginForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-login";
    }

    // Student Login - POST
    @PostMapping("/student-login")
    public String loginStudent(@ModelAttribute("student") Student student, Model model, HttpSession session) {
        Student dbStudent = studentService.findByEmail(student.getEmail());
        if (dbStudent != null && dbStudent.getPassword().equals(student.getPassword())) {
            if ("STUDENT".equalsIgnoreCase(dbStudent.getRole())) {
                // Store the whole student object in the session
                session.setAttribute("loggedInStudent", dbStudent);  // Set the entire student object
                return "redirect:/student-dashboard"; // Redirect to student dashboard
            }
        }
        model.addAttribute("error", "Invalid credentials");
        return "student-login";
    }

    // Admin Login Page
    @GetMapping("/admin-login")
    public String showAdminLoginForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin-login";
    }

    // Admin Login - POST
    @PostMapping("/admin-login")
    public String loginAdmin(@ModelAttribute("admin") Admin admin, Model model, HttpSession session) {
        Admin dbAdmin = adminService.findByEmail(admin.getEmail());
        if (dbAdmin != null && dbAdmin.getPassword().equals(admin.getPassword())) {
            if ("ADMIN".equalsIgnoreCase(dbAdmin.getRole())) {
                session.setAttribute("loggedInAdmin", dbAdmin);  // Set the whole admin object in session
                return "redirect:/admin-dashboard"; // Redirect to admin dashboard
            }
        }
        model.addAttribute("error", "Invalid credentials");
        return "admin-login";
    }

    // Student Dashboard
    @GetMapping("/student-dashboard")
    public String studentDashboard(HttpSession session) {
        // Check if the student is logged in by session attribute
        if (session.getAttribute("loggedInStudent") == null) {
            return "redirect:/login"; // Redirect to login if not logged in
        }
        return "student-dashboard"; // templates/student-dashboard.html
    }

    // Admin Dashboard
    @GetMapping("/admin-dashboard")
    public String adminDashboard(HttpSession session) {
        // Check if the admin is logged in by session attribute
        if (session.getAttribute("loggedInAdmin") == null) {
            return "redirect:/login"; // Redirect to login if not logged in
        }
        return "admin-dashboard"; // templates/admin-dashboard.html
    }

    // Handle Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate session to log out the user
        return "redirect:/login"; // Redirect to login page after logout
    }
}
