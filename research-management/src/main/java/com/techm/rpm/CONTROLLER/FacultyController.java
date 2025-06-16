package com.techm.rpm.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techm.rpm.MODEL.Faculty;
import com.techm.rpm.SERVICE.FacultyService;


@Controller
public class FacultyController {
/*
    @Autowired
    private FacultyService facultyService;
    
    @GetMapping("/")
    public String showLoginPage() {
        return "facultylogin"; 
    }

    @PostMapping("/login")
    public String login(@RequestParam String facultyEmail, @RequestParam String facultyPassword, Model model) {
        Faculty faculty = facultyService.authenticateFaculty(facultyEmail, facultyPassword);
        
        if (faculty != null) {
            // Successful login
            model.addAttribute("faculty", faculty);
            return "dashboard"; // Redirect to a dashboard or main page
        } else {
            // Failed login
            model.addAttribute("status", "failed");
            return "facultylogin"; // Show login page again with error
        }
    }*/
}
