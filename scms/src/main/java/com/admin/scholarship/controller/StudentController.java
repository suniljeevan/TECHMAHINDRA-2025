package com.admin.scholarship.controller;

import com.admin.scholarship.model.Student;
import com.admin.scholarship.repository.ScholarshipRepository;
import com.admin.scholarship.repository.StudentRepository;
import com.admin.scholarship.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;

    @GetMapping("/apply")
    public String showApplyForm(Student student ,Model model) {
    	model.addAttribute("student", new Student());
    	model.addAttribute("scholarships", scholarshipRepository.findAll());
    	student.setTenthPercentage(null);  
        student.setTwelfthPercentage(null); 
        student.setIncome(null); 
        return "student/apply"; 
    }

    @GetMapping("/login")
    public String logoutAndRedirect() {
        
        return "/login"; 
    }
    
    @Autowired
    private StudentService studentService;


    @GetMapping("/student/searchStudent")
    @ResponseBody
    public String getStudentStatus(@RequestParam String email) {
        Student student = studentService.findByEmail(email);
        if (student != null) {
            return student.getStatus().toString();
        } else {
            return "Student not found";
        }
    }
    
    @PostMapping("/apply")
    public String submitForm(@ModelAttribute Student student, Model model) {
        student.setStatus(Student.Status.PENDING);
        studentRepository.save(student);
        model.addAttribute("message", "Application submitted successfully!");
        return "/student/home"; 
    }
    @Autowired
    private ScholarshipRepository scholarshipRepository;

    @GetMapping("/student/home")
    public String showStudentHome(Model model) {
        model.addAttribute("scholarships", scholarshipRepository.findAll());
        return "/student/home";
    }

    
    
}

