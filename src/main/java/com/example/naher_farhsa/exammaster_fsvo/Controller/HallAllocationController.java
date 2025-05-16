package com.example.naher_farhsa.exammaster_fsvo.Controller;

import com.example.naher_farhsa.exammaster_fsvo.DTO.AllocationSummaryDTO;
import com.example.naher_farhsa.exammaster_fsvo.DTO.HallAllocationDTO;
import com.example.naher_farhsa.exammaster_fsvo.Entity.HallAllocation;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Course;
import com.example.naher_farhsa.exammaster_fsvo.Service.ExamService;
import com.example.naher_farhsa.exammaster_fsvo.Service.HallAllocationService;
import com.example.naher_farhsa.exammaster_fsvo.Entity.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            return "redirect:/hallAllocation/get?courseCode=" + courseCode;
        }

        // Fetch available courses from the enum and pass it to the view
        model.addAttribute("courses", hallAllocationService.getAvailableCourses());
        return "select-hall-course";
    }

    // View Hall Allocation by Course
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
        return "view-hall";  // Thymeleaf template for viewing allocations
    }

    @GetMapping("/select-exam-course")
    public String selectCourseForHallSeatAllocation(@RequestParam(value = "courseCode", required = false) String courseCode, Model model) {
        if (courseCode != null && !courseCode.isEmpty()) {
            return "redirect:/hallAllocation/view-seats?courseCode=" + courseCode;
        }

        model.addAttribute("courses", hallAllocationService.getAvailableCourses());
        return "select-hall-seat-course";
    }

    @GetMapping("/view-seats")
    public String viewHallSeats(@RequestParam("courseCode") String courseCode, Model model) {
        Course course;
        try {
            course = Course.valueOf(courseCode);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid course ID: " + courseCode);
        }

        List<HallAllocation> allocations = hallAllocationService.getHallSeatAllocationsByCourse(course);

        // Generate formatted seat IDs with alternating A/B pattern
        List<HallAllocationDTO.SeatInfo> seatInfoList = new ArrayList<>();
        boolean isA = true;
        int seatNumber = 1;

        for (HallAllocation alloc : allocations) {
            String seatType = isA ? "A" : "B";
            String seatId = String.format("%s%sS%02d",
                    alloc.getExam().getHallId(),
                    seatType,
                    seatNumber);

            seatInfoList.add(new HallAllocationDTO.SeatInfo(
                    seatId,
                    alloc.getStudent().getStudentName(),
                    alloc.getStudent().getStudentId()
            ));

            isA = !isA;
            if (isA) seatNumber++;
        }

        AllocationSummaryDTO summary = hallAllocationService.getAllocationSummary(courseCode);

        model.addAttribute("seatInfoList", seatInfoList);
        model.addAttribute("summary", summary);
        model.addAttribute("courseCode", courseCode);

        return "view-hall-seats";
    }





    
}









