package com.org.TMS.controller;

import com.org.TMS.model.Student;
import com.org.TMS.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public String viewStudents(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "students";  // Return the HTML page displaying the list of students
    }

    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";  // HTML page for adding a new student
    }

    @PostMapping
    public String saveStudent(@ModelAttribute Student student) {
        service.saveStudent(student);
        return "redirect:/students";  // Redirect to the list of students after saving
    }

    @GetMapping("/edit/{sid}")
    public String editForm(@PathVariable String sid, Model model) {
        Student student = service.getStudentById(sid);
        if (student != null) {
            model.addAttribute("student", student);
            return "edit-student";  // HTML page for editing a student
        }
        return "redirect:/students";  // Redirect if student not found
    }

    @PostMapping("/{sid}/update")
    public String updateStudent(@PathVariable String sid, @ModelAttribute Student student) {
        // Retrieve the existing student by SID
        Student existingStudent = service.getStudentById(sid);

        if (existingStudent != null) {
            // Update the existing student's fields with the new data
            existingStudent.setSname(student.getSname());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setContact(student.getContact());
            existingStudent.setLocation(student.getLocation());
            
            // Save the updated student
            service.saveStudent(existingStudent);
        }

        return "redirect:/students";  // Redirect to the list of students after updating
    }

    @GetMapping("/delete/{sid}")
    public String deleteStudent(@PathVariable String sid) {
        service.deleteStudent(sid);  // Delete student by SID
        return "redirect:/students";  // Redirect to the list of students after deletion
    }
}
