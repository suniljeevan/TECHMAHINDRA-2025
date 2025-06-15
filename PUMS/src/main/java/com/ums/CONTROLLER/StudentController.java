package com.ums.CONTROLLER;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ums.MODEL.Student;
import com.ums.SERVICE.StudentService;
@Controller
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/students")
    public String viewStudents(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "students";
    }
    @GetMapping("/student/dashboard")
    public String studentHome(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("name", userDetails.getUsername()); // Add student name
        }
        return "student-dashboard"; // returns student-dashboard.html
    }

    @GetMapping("/student/courses")
    public String viewCourses() {
        // Logic to display courses
        return "student-courses"; // A page for listing courses
    }

    @GetMapping("/student/grades")
    public String viewGrades() {
        // Logic to display grades
        return "student-grades"; // A page for displaying grades
    }

    @GetMapping("/student/resources")
    public String viewResources() {
        // Logic to display resources
        return "student-resources"; // A page for accessing resources
    }
    @GetMapping("/students/new")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute Student student) {
        service.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/students/edit/{sid}")
    public String editForm(@PathVariable long sid, Model model) {
        model.addAttribute("student", service.getStudentById(sid));
        return "edit-student";
    }

    @PostMapping("/students/{sid}/update")
    public String updateStudent(@PathVariable long sid, @ModelAttribute Student student) {
        student.setSid(sid);
        service.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/students/delete/{sid}")
    public String deleteStudent(@PathVariable long sid) {
        service.deleteStudent(sid);
        return "redirect:/";
    }
}