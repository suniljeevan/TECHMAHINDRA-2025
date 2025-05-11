package com.example.naher_farhsa.exammaster_fsvo.Controller;

import com.example.naher_farhsa.exammaster_fsvo.Entity.Exam;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Course;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Hall;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Shift;
import com.example.naher_farhsa.exammaster_fsvo.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    // 1. Show Add Exam Form
    @GetMapping("/add")
    public String showAddExamForm(Model model) {
        model.addAttribute("exam", new Exam());

        // 1. Fetch already used course and hall IDs
        List<Course> usedCourses = examService.getAssignedCourses();
        List<Hall> usedHalls = examService.getAssignedHalls();

        // 2. Filter unused courses and halls
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

        // 3. Add data to model
        model.addAttribute("courses", availableCourses);
        model.addAttribute("halls", availableHalls);
        model.addAttribute("shifts", Shift.values());

        return "exam-add-form";
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
        return "redirect:/exam/getExams";  // Redirect to the exam list after success
    }

    @GetMapping("/getExams")
    public String listExams(Model model) {
        List<Exam> exams = examService.getAllExams();
        model.addAttribute("exams", exams);
        return "exam-list";  // Thymeleaf template for exam list
    }

}
