package com.project.controller;

import com.project.model.Job;
import com.project.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/jobs")
public class AdminJobController {

    @Autowired
    private JobRepository jobRepository;

    // List all jobs
    @GetMapping
    public String listJobs(Model model) {
        model.addAttribute("jobs", jobRepository.findAll());
        return "admin-job-listings";  // job-listings.html
    }

    // Show form to add a new job
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("job", new Job());
        return "job-form"; // job-form.html
    }

    // Save a new or updated job
    @PostMapping("/save")
    public String saveJob(@ModelAttribute("job") Job job) {
        jobRepository.save(job);  // Save or update the job in the database
        return "redirect:/admin/jobs";  // Redirect back to the job listings page
    }

    // Show form to edit an existing job
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid job ID: " + id));
        model.addAttribute("job", job);
        return "job-form";  // Reuse the job form for editing
    }

    // Delete a job
    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable("id") Long id) {
        jobRepository.deleteById(id);
        return "redirect:/admin/jobs";  // Redirect back to the job listings page after deletion
    }
}
