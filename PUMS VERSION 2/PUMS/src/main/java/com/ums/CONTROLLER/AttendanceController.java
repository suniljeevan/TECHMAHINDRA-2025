package com.ums.CONTROLLER;
import com.ums.MODEL.Attendance;
import com.ums.MODEL.Course;
import com.ums.MODEL.Student;
import com.ums.SERVICE.AttendanceService;
import com.ums.SERVICE.CourseService;
import com.ums.SERVICE.CourseServiceImpl;
import com.ums.SERVICE.StudentService;
import com.ums.SERVICE.StudentServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/attendances")
public class AttendanceController {
	@Autowired
    private AttendanceService attendanceService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
    @GetMapping
    public String listAttendances(Model model) {
    	List<Attendance> attendances = attendanceService.getAll();
        model.addAttribute("attendances", attendances);
        return "attendances";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
    	model.addAttribute("attendance", new Attendance());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", courseService.getAllCourses());
        return "add-attendance";
    }

    @PostMapping
    public String saveAttendance(@ModelAttribute Attendance attendance) {
    	attendanceService.save(attendance);
        return "redirect:/attendances";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
    	Attendance attendance = attendanceService.get(id);
    	model.addAttribute("attendance", attendance);
    	model.addAttribute("students", studentService.getAllStudents());
    	model.addAttribute("courses", courseService.getAllCourses());
        return "edit-attendance";
    }

    @PostMapping("{id}/update")
    public String update(@PathVariable Long id,@ModelAttribute Attendance attendance) {
    	attendance.setId(id);
    	attendanceService.save(attendance);
        return "redirect:/attendances";
    }

    @GetMapping("/delete/{id}")
    public String deleteAttendance(@PathVariable Long id) {
        attendanceService.delete(id);
        return "redirect:/attendances";
    }
}  

