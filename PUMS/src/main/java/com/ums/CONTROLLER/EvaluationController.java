package com.ums.CONTROLLER;
import com.ums.MODEL.Attendance;
import com.ums.MODEL.Evaluation;
import com.ums.SERVICE.CourseService;
import com.ums.SERVICE.ExamService;
import com.ums.SERVICE.EvaluationService;
import com.ums.SERVICE.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/evaluations")
public class EvaluationController {
	@Autowired
    private EvaluationService evaluationService;
	@Autowired
    private StudentService studentService;
	@Autowired
    private ExamService examService;

    @GetMapping
    public String listEvaluations(Model model) {
    	List<Evaluation> results = evaluationService.getAll();
        model.addAttribute("evaluations", results); 
        return "evaluations";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
    	model.addAttribute("evaluation", new Evaluation());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", studentService.getAllStudents());
        model.addAttribute("exams", examService.getAll());
        return "add-evaluation";
    }

    @PostMapping
    public String saveResult(@ModelAttribute Evaluation result) {
    	evaluationService.save(result);
        return "redirect:/evaluations";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
    	Evaluation evaluation = evaluationService.get(id);
    	model.addAttribute("evaluation", evaluation);
    	model.addAttribute("students", studentService.getAllStudents());
    	model.addAttribute("exams", examService.getAll());
    	return "edit-evaluation";
    }

    @PostMapping("{id}/update")
    public String updateResult(@PathVariable Long id, @ModelAttribute Evaluation evaluation) {
    	evaluation.setEvaluationId(id);
        evaluationService.save(evaluation);
        return "redirect:/evaluations";
    }

    @GetMapping("/delete/{id}")
    public String deleteResult(@PathVariable Long id) {
    	evaluationService.delete(id);
        return "redirect:/evaluations";
    }
}  

