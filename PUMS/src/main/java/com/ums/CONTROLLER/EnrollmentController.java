package com.ums.CONTROLLER;
import com.ums.MODEL.Enrollment;
import com.ums.MODEL.User;
import com.ums.SERVICE.CourseService;
import com.ums.SERVICE.EnrollmentService;
import com.ums.SERVICE.StudentService;
import com.ums.SERVICE.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {
	@Autowired
    private EnrollmentService enrollmentService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;

    @GetMapping
    public String listEnrollments(Model model) {
    	List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        model.addAttribute("enrollments", enrollments);
        return "enrollments";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
    	model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", courseService.getAllCourses());
        return "add-enrollment";
    }

    @PostMapping
    public String saveEnrollment(@ModelAttribute Enrollment enrollment) {
        enrollmentService.saveEnrollment(enrollment);
        return "redirect:/enrollments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
    	model.addAttribute("enrollment", enrollment);
    	model.addAttribute("students", studentService.getAllStudents());
    	model.addAttribute("courses", courseService.getAllCourses());
        return "edit-enrollment";
    }
    @PostMapping("{id}/update")
    public String updateEnrollment(@PathVariable Long id, @ModelAttribute Enrollment enrollment) {
        enrollment.setId(id);
        enrollmentService.saveEnrollment(enrollment);
        return "redirect:/enrollments";
    }

    @GetMapping("/delete/{id}")
    public String deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return "redirect:/enrollments";
    }
}  
