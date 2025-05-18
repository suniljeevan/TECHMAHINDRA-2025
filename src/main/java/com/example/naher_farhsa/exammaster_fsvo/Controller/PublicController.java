package com.example.naher_farhsa.exammaster_fsvo.Controller;

import com.example.naher_farhsa.exammaster_fsvo.DTO.AllocationSummaryDTO;
import com.example.naher_farhsa.exammaster_fsvo.DTO.HallAllocationDTO;
import com.example.naher_farhsa.exammaster_fsvo.Service.HallAllocationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/exammaster")
public class PublicController {

    @Autowired
    private HallAllocationService hallAllocationService;

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response) throws IOException {
        // Invalidate the JWT cookie by setting maxAge to 0
        Cookie cookie = new Cookie("jwt", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Delete cookie

        response.addCookie(cookie);
        response.sendRedirect("home");


    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }



    // admin dashboard

    @GetMapping("/dashboard")
    public String showDashboardPage() {
        return "dashboard";
    }




    // student dashboard

    @GetMapping("/studentDashboard")
    public String showStudentDashboardPage() {
        return "student-dashboard";
    }

    @GetMapping("/student-select-course")
    public String selectCourseForAllocation(@RequestParam(value = "courseCode", required = false) String courseCode, Model model) {
        if (courseCode != null && !courseCode.isEmpty()) {
            // Redirect to the view with the selected course code
            return "redirect:/exammaster/get?courseCode=" + courseCode;
        }

        // Fetch available courses from the enum and pass it to the view
        model.addAttribute("courses", hallAllocationService.getAvailableCourses());
        return "student-select-hall-course";
    }

    @GetMapping("/get")
    public String viewHallAllocations(@RequestParam(required = true) String courseCode, Model model) {
        if (courseCode != null && !courseCode.isEmpty()) {
            // Fetch hall allocation and summary for the given course code
            List<HallAllocationDTO> allocations = hallAllocationService.getHallAllocationByExamCourse(courseCode);
            AllocationSummaryDTO summary = hallAllocationService.getAllocationSummary(courseCode);

            // Add data to the model
            model.addAttribute("allocations", allocations);
            model.addAttribute("summary", summary);
            model.addAttribute("courseCode", courseCode);
        }
        return "student-view-hall";  // Thymeleaf template for viewing allocations
    }








}
