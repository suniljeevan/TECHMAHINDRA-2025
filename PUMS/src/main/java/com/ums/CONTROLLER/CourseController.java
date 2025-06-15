package com.ums.CONTROLLER;
import com.ums.MODEL.Attendance;
import com.ums.MODEL.Course;
import com.ums.MODEL.Department;
import com.ums.MODEL.Faculty;
import com.ums.SERVICE.CourseService;
import com.ums.SERVICE.FacultyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/courses")
public class CourseController {
	@Autowired
    private CourseService courseService;
	@Autowired 
	private FacultyService facultyService;

    @GetMapping
    public String listCourses(Model model) {
    	List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
    	List<Faculty> faculties = facultyService.getAll();
    	model.addAttribute("faculties", faculties);
    	model.addAttribute("course", new Course());
     // Adding Courses lists
        model.addAttribute("courses", courseService.getAllCourses());
        return "add-course";
    }

    @PostMapping
    public String saveCourse(@ModelAttribute Course course) {
    	Faculty faculty = facultyService.get(course.getFaculty().getFacultyId());  // Fetch the Faculty
        course.setFaculty(faculty);
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
    	Course course = courseService.getCourseById(id);
    	List<Faculty> faculties = facultyService.getAll();
    	model.addAttribute("course", course);
    	 model.addAttribute("faculties", faculties);
        return "edit-course";
    }

    @PostMapping("{id}/update")
    public String updateCourse(@PathVariable Long id, @ModelAttribute Course course) {
        course.setCid(id);
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}  

