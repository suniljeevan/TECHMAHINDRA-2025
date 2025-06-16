package com.project.controller;

import com.project.model.Application;
import com.project.model.ApplicationStatus;
import com.project.model.Job;
import com.project.model.Student;
import com.project.repository.ApplicationRepository;
import com.project.repository.JobRepository;
import com.project.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    // Display list of job listings
    @GetMapping("/job-listings")
    public String showJobListings(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("loggedInStudent");

        if (student == null) {
            return "redirect:/login";  // Redirect to login page if student is not logged in
        }

        // Fetch all jobs
        List<Job> jobs = jobRepository.findAll();

        // Pass the application status for each job directly to the view
        for (Job job : jobs) {
            Application application = applicationRepository.findByJobAndStudent(job, student);
            if (application != null) {
                // Set the application status to the enum value
                job.setApplicationStatus(application.getStatus());
            } else {
                job.setApplicationStatus(ApplicationStatus.NOT_APPLIED); // Default enum value for "Not Applied"
            }
        }

        model.addAttribute("jobs", jobs);
        return "job-listings";  // Thymeleaf template for displaying jobs
    }

    // Show the application form for a selected job
    @GetMapping("/apply/{jobId}")
    public String showApplicationForm(@PathVariable Long jobId, Model model, HttpSession session) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Student student = (Student) session.getAttribute("loggedInStudent");

        if (student == null) {
            return "redirect:/login";  // Redirect to login if not logged in
        }

        Application application = new Application();
        application.setJob(job);
        application.setName(student.getName());
        application.setEmail(student.getEmail());
        application.setCollegeName(""); // Set empty string to allow manual input of college name
        application.setCgpa(null); // Set CGPA to null to allow manual entry

        model.addAttribute("applicationForm", application);
        model.addAttribute("student", student);

        return "application-form"; // Display the application form
    }


    // Handle application form submission
    @PostMapping("/apply")
    public String submitApplication(@ModelAttribute("applicationForm") Application application, Model model, HttpSession session) {
        Student sessionStudent = (Student) session.getAttribute("loggedInStudent");

        if (sessionStudent == null) {
            return "redirect:/login";
        }

        Student student = studentRepository.findById(sessionStudent.getId())
            .orElseThrow(() -> new RuntimeException("Student not found"));

        Job job = jobRepository.findById(application.getJob().getId())
            .orElseThrow(() -> new RuntimeException("Job not found"));

        // Ensure CGPA is captured from the form and update student's CGPA
        if (application.getCgpa() != null && application.getCgpa() > 0) {
            student.setCgpa(application.getCgpa());  // Update student's CGPA based on the application form
            studentRepository.save(student);  // Save the updated student object with new CGPA
        } else {
            model.addAttribute("message", "Please enter a valid CGPA.");
            model.addAttribute("applicationForm", application);
            return "application-form";
        }

        Application existingApplication = applicationRepository.findByJobAndStudent(job, student);
        if (existingApplication != null) {
            model.addAttribute("message", "You have already applied for this job.");
            model.addAttribute("applicationForm", application);
            return "application-form";
        }

        application.setStudent(student);
        application.setJob(job);
        application.setStatus(ApplicationStatus.APPLIED);

        // Save the application
        applicationRepository.save(application);

        return "redirect:/job-listings"; // Redirect to job listings page after successful application
    }
}
