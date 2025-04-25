package com.ums.CONTROLLER;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ums.MODEL.Student;
import com.ums.SERVICE.StudentService;



@Controller
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/students")
    public String viewStudents(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute Student student) {
        service.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/students/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("student", service.getStudentById(id));
        return "edit-student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable String id, @ModelAttribute Student student) {
        student.setSid(id);
        service.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        service.deleteStudent(id);
        return "redirect:/";
    }
}