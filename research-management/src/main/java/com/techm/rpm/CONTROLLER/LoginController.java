package com.techm.rpm.CONTROLLER;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techm.rpm.MODEL.Admin;
import com.techm.rpm.MODEL.Faculty;
import com.techm.rpm.REPOSITORY.AdminRepository;
import com.techm.rpm.REPOSITORY.FacultyRepository;
import com.techm.rpm.SERVICE.ProjectService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private FacultyRepository facultyRepo;
    @Autowired
    private ProjectService projectService;
    
    @GetMapping("/")
    public String showLoginPage() {
        return "home"; 
    }
    @GetMapping("/viewlogin")
    public String showLoginForm() {
        return "login"; // login.jsp or login.html in templates/
    }

        
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password,
                        @RequestParam String role, Model model, HttpSession session) {
        if ("admin".equalsIgnoreCase(role)) {
            Optional<Admin> admin = adminRepo.findByAdminEmailAndAdminPassword(email, password);
            if (admin.isPresent()) {
                session.setAttribute("admin", admin.get());

                model.addAttribute("admin", admin.get());
                model.addAttribute("totalProjects", projectService.getTotalProjects());
                model.addAttribute("completedProjects", projectService.getCompletedProjects());
                model.addAttribute("fundedProjects", projectService.getFundedProjects());

                double totalAmount = projectService.getTotalFundedAmount(); // or getTotalfacultyFundedAmount
                String formattedAmount = "₹" + formatIndianCurrency(totalAmount);
                model.addAttribute("totalFundedAmount", formattedAmount);


                model.addAttribute("countCseProjects", projectService.countCseProjects());
                model.addAttribute("countEceProjects", projectService.countEceProjects());
                model.addAttribute("countEeProjects", projectService.countEeProjects());
                model.addAttribute("countMeProjects", projectService.countMeProjects());
                model.addAttribute("countCeProjects", projectService.countCeProjects());
                model.addAttribute("countInhouseProjects", projectService.countInhouseProjects());
                return "admin/new-admin-dashboard";
            }
        } else if ("faculty".equalsIgnoreCase(role)) {
            Optional<Faculty> faculty = facultyRepo.findByFacultyEmailAndFacultyPassword(email, password);
            if (faculty.isPresent()) {
                session.setAttribute("faculty", faculty.get());
                model.addAttribute("faculty", faculty.get());
                model.addAttribute("totalProjects", projectService.getTotalfacultyProjects());
                model.addAttribute("completedProjects", projectService.getCompletedfacultyProjects());
                model.addAttribute("fundedProjects", projectService.getFundedfacultyProjects());

                double totalAmount = projectService.getTotalfacultyFundedAmount(); // or getTotalfacultyFundedAmount
                String formattedAmount = "₹" + formatIndianCurrency(totalAmount);
                model.addAttribute("totalFundedAmount", formattedAmount);


                model.addAttribute("countCseProjects", projectService.countfacultyCseProjects());
                model.addAttribute("countEceProjects", projectService.countfacultyEceProjects());
                model.addAttribute("countEeProjects", projectService.countfacultyEeProjects());
                model.addAttribute("countMeProjects", projectService.countfacultyMeProjects());
                model.addAttribute("countCeProjects", projectService.countfacultyCeProjects());
                model.addAttribute("countInhouseProjects", projectService.countfacultyInhouseProjects());
                return "admin/new-admin-dashboard";
            }
            
        }

        model.addAttribute("status", "failed");
        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // clear session
        return "redirect:/viewlogin";
    }
    
    private String formatIndianCurrency(double amount) {
        String amountStr = String.format("%.2f", amount);
        String[] parts = amountStr.split("\\.");
        String intPart = parts[0];
        String decPart = parts[1];

        // Apply Indian format to the integer part
        char[] digits = intPart.toCharArray();
        StringBuilder result = new StringBuilder();

        int len = digits.length;
        int count = 0;

        for (int i = len - 1; i >= 0; i--) {
            result.append(digits[i]);
            count++;
            if (i > 0) {
                if ((count == 3) || (count > 3 && (count - 3) % 2 == 0)) {
                    result.append(",");
                }
            }
        }

        // Reverse to get final formatted value
        result.reverse().append(".").append(decPart);
        return result.toString();
    }

}
