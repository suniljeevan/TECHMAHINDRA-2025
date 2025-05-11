package com.example.naher_farhsa.exammaster_fsvo.Controller;

import com.example.naher_farhsa.exammaster_fsvo.DTO.AllocationSummaryDTO;
import com.example.naher_farhsa.exammaster_fsvo.DTO.HallAllocationDTO;
import com.example.naher_farhsa.exammaster_fsvo.Service.ExamService;
import com.example.naher_farhsa.exammaster_fsvo.Service.HallAllocationService;
import com.example.naher_farhsa.exammaster_fsvo.Entity.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hallAllocation")
public class HallAllocationController {

    @Autowired
    private HallAllocationService hallAllocationService;

    @Autowired
    private ExamService examService;

    // Select Course for Hall Allocation and handle selection
    @GetMapping("/select-course")
    public String selectCourseForAllocation(@RequestParam(value = "courseCode", required = false) String courseCode, Model model) {
        if (courseCode != null && !courseCode.isEmpty()) {
            // Redirect to the view with the selected course code
            return "redirect:/hallAllocation/view?courseCode=" + courseCode;
        }

        // Fetch available courses from the enum and pass it to the view
        model.addAttribute("courses", hallAllocationService.getAvailableCourses());
        return "select-courses";  // Thymeleaf template for selecting course
    }


    // View Hall Allocation by Course
    @GetMapping("/view")
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
        return "view-hall-allocations";  // Thymeleaf template for viewing allocations
    }





}
