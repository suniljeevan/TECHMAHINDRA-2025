package com.admin.scholarship.controller;

import com.admin.scholarship.model.Student;
import com.admin.scholarship.model.Scholarship;
import com.admin.scholarship.service.StudentService;
import com.admin.scholarship.service.ScholarshipService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScholarshipService scholarshipService;

    @GetMapping("/applications")
    public String viewApplications(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("totalAmount", studentService.getTotalSanctionedAmount());
        model.addAttribute("totalApplications", studentService.getAllStudents().size());
        return "admin/applications";
    }

    @PostMapping("/approve/{id}")
    public String approve(@PathVariable int id) {
        studentService.updateStatus(id, Student.Status.APPROVED);
        return "redirect:/admin/applications";
    }

    @PostMapping("/reject/{id}")
    public String reject(@PathVariable int id) {
        studentService.updateStatus(id, Student.Status.REJECTED);
        return "redirect:/admin/applications";
    }

    @GetMapping("/dashboard")
    public String viewDashboard(Model model) {
        double totalAmount = studentService.getTotalSanctionedAmount();
        int totalApplications = studentService.getAllStudents().size();
        int approvedCount = studentService.countByStatus(Student.Status.APPROVED);
        int pendingCount = studentService.countByStatus(Student.Status.PENDING);

        Map<String, Long> typeCountMap = studentService.getScholarshipTypeCounts();
        Map<String, Long> approvedTypeCountMap = studentService.getApprovedScholarshipTypeCounts();

        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("totalApplications", totalApplications);
        model.addAttribute("approvedCount", approvedCount);
        model.addAttribute("pendingCount", pendingCount);
        model.addAttribute("typeCounts", typeCountMap);
        model.addAttribute("approvedTypeCounts", approvedTypeCountMap);

        return "/admin/dashboard";
    }

    @GetMapping("/logout")
    public String logout() {
        return "admin/logout";
    }

  
    @GetMapping("/scholarships")
    public String viewScholarships(Model model) {
        model.addAttribute("scholarships", scholarshipService.getAllScholarships());
        return "admin/scholarships";
    }
    @GetMapping("/add-scholarship")
    public String addScholarshipForm() {
        return "admin/addscholarship";
    }

    @PostMapping("/save-scholarship")
    public String saveScholarship(@ModelAttribute Scholarship scholarship) {
        scholarshipService.saveScholarship(scholarship);
        return "redirect:/admin/scholarships";
    }

    @PostMapping("/update-scholarship/{id}")
    public String updateScholarship(@PathVariable Long id, @ModelAttribute Scholarship updated) {
        scholarshipService.updateScholarship(id, updated);
        return "redirect:/admin/scholarships";
    }
    
    @PostMapping("/delete-scholarship/{id}")
    public String deleteScholarship(@PathVariable Long id) {
        scholarshipService.deleteScholarship(id);
        return "redirect:/admin/scholarships";
    }


}
