package com.project.controller;

import com.project.model.Profile;
import com.project.model.Student;
import com.project.service.ProfileService;
import com.project.service.StudentService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private StudentService studentService;

    // View Profile
    @GetMapping
    public String viewProfile(Model model, HttpSession session) {
        // Get the logged-in student from the session
        Student loggedInStudent = (Student) session.getAttribute("loggedInStudent");

        if (loggedInStudent == null) {
            return "redirect:/login"; // Redirect to login if session expired or not logged in
        }

        Profile profile = profileService.getProfileByStudent(loggedInStudent);

        if (profile == null) {
            return "redirect:/profiles/edit"; // Redirect to create profile if none exists
        }

        model.addAttribute("profile", profile);
        return "student-profile"; // Return the view for student profile
    }

    // Edit Profile Form
    @GetMapping("/edit")
    public String editProfileForm(Model model, HttpSession session) {
        // Get the logged-in student from the session
        Student loggedInStudent = (Student) session.getAttribute("loggedInStudent");

        if (loggedInStudent == null) {
            return "redirect:/login"; // Redirect to login if session expired or not logged in
        }

        Profile profile = profileService.getProfileByStudent(loggedInStudent);

        if (profile == null) {
            profile = new Profile(); // Create a new profile if none exists
            profile.setStudent(loggedInStudent);
        }

        model.addAttribute("profile", profile);
        return "edit-student-profile"; // Return the edit profile form
    }

    // Update Profile
    @PostMapping("/edit")
    public String updateProfile(@ModelAttribute Profile profile, HttpSession session) {
        // Get the logged-in student from the session
        Student loggedInStudent = (Student) session.getAttribute("loggedInStudent");

        if (loggedInStudent == null) {
            return "redirect:/login"; // Redirect to login if session expired or not logged in
        }

        profile.setStudent(loggedInStudent); // Associate the profile with the logged-in student
        profileService.saveOrUpdateProfile(profile);

        return "redirect:/profiles"; // Redirect to the profile view after updating
    }
}
