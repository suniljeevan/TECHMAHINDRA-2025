package com.example.naher_farhsa.exammaster_fsvo.Controller;

import com.example.naher_farhsa.exammaster_fsvo.Entity.Exam;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Course;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Hall;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Shift;
import com.example.naher_farhsa.exammaster_fsvo.Service.ExamService;
import com.example.naher_farhsa.exammaster_fsvo.Service.HallAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

import java.util.List;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private HallAllocationService hallAllocationService;

    // Show Add Exam Form
    @GetMapping("/add")
    public String showAddExamForm(Model model) {
        model.addAttribute("exam", new Exam());

        //Fetch already used course and hall IDs
        List<Course> usedCourses = examService.getAssignedCourses();
        List<Hall> usedHalls = examService.getAssignedHalls();

        //  Filter unused courses and halls
        List<Course> availableCourses = new ArrayList<>();
        for (Course c : Course.values()) {
            if (!usedCourses.contains(c)) {
                availableCourses.add(c);
            }
        }

        List<Hall> availableHalls = new ArrayList<>();
        for (Hall h : Hall.values()) {
            if (!usedHalls.contains(h)) {
                availableHalls.add(h);
            }
        }

        //Add data to model
        model.addAttribute("courses", availableCourses);
        model.addAttribute("halls", availableHalls);
        model.addAttribute("shifts", Shift.values());

        return "add-exam";
    }


    @PostMapping("/add")
    public String addExam(@ModelAttribute Exam exam, RedirectAttributes redirectAttributes) {
        try {
            examService.addExam(exam);
            redirectAttributes.addFlashAttribute("success", "Exam added successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/exam/add";  // Redirect back to the add exam form with the error
        }
        return "redirect:/exam/get";  // Redirect to the exam list after success
    }

    @GetMapping("/get")
    public String listExams(Model model) {
        List<Exam> exams = examService.getAllExams();
        model.addAttribute("exams", exams);
        return "view-exam";
    }

    @PostMapping("/select-course")
    public String showDeleteConfirmation(@RequestParam("courseId") Course courseId, Model model) {
        model.addAttribute("courses", List.of(courseId));  // Only selected course shown
        return "select-exam-course";  // Show confirmation form
    }


    @PostMapping("/delete")
    public String deleteExamByCourse(@RequestParam Course courseId) {
        hallAllocationService.deleteHallAllocationByExamCourse(courseId);
        examService.deleteByCourse(courseId);
        return "redirect:/exam/get";
    }


}
