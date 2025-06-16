package com.project.controller;

import com.project.model.Application;
import com.project.model.ApplicationStatus;
import com.project.service.AdminApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminApplicationController {

    @Autowired
    private AdminApplicationService adminApplicationService;

    // View all applications
    @GetMapping("/applications")
    public String viewAllApplications(Model model) {
        List<Application> applications = adminApplicationService.getAllApplications(); // Fetch all applications
        model.addAttribute("applications", applications); // Add the list of applications to the model
        return "admin-applications"; // Ensure you have a corresponding template
    }

    // View application details by ID
    @GetMapping("/applications/{id}")
    public String viewApplicationDetails(@PathVariable Long id, Model model) {
        Application application = adminApplicationService.getApplicationById(id);
        if (application == null) {
            return "redirect:/admin/applications"; // Redirect if application is not found
        }
        model.addAttribute("application", application); // Add the application object to the model
        return "admin-application-detail"; // This should correspond to the Thymeleaf template (You can delete this if not used)
    }

    // Update application status
    @PostMapping("/applications/{id}/update-status")
    public String updateApplicationStatus(@PathVariable Long id, @RequestParam String status) {
        Application application = adminApplicationService.getApplicationById(id);
        if (application != null) {
            try {
                // Convert the string to ApplicationStatus enum
                ApplicationStatus applicationStatus = ApplicationStatus.valueOf(status);
                application.setStatus(applicationStatus);  // Update the status
                adminApplicationService.save(application);  // Save the updated application
            } catch (IllegalArgumentException e) {
                // Handle invalid status value if necessary
                return "redirect:/admin/applications?error=invalidStatus"; // Redirect with error if status is invalid
            }
        }
        return "redirect:/admin/applications"; // Redirect back to the applications list
    }
}
