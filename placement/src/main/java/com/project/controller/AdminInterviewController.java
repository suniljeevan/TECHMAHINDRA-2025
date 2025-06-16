package com.project.controller;

import com.project.model.Application;
import com.project.model.Interview;
import com.project.service.AdminInterviewService;
import com.project.service.AdminApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminInterviewController {

    @Autowired
    private AdminInterviewService adminInterviewService;

    @Autowired
    private AdminApplicationService adminApplicationService;

    @GetMapping("/admin/schedule-interview")
    public String showScheduleInterviewForm(Model model) {
        model.addAttribute("applications", adminApplicationService.getAcceptedApplications());
        model.addAttribute("interview", new Interview());
        return "schedule-interview";
    }

    @PostMapping("/admin/schedule-interview")
    public String scheduleInterview(@ModelAttribute Interview interview, Model model) {
        Application fullApp = adminApplicationService.getApplicationById(interview.getApplication().getId());
        interview.setApplication(fullApp);
        adminInterviewService.scheduleInterview(interview);

        model.addAttribute("message", "Interview Scheduled Successfully for " + fullApp.getStudent().getName());
        return "schedule-interview"; // Returning the same page with a success message
    }
}
