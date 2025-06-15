package com.ums.CONTROLLER;
import com.ums.MODEL.Course;
import com.ums.MODEL.Exam;
import com.ums.MODEL.Faculty;
import com.ums.SERVICE.CourseService;
import com.ums.SERVICE.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/exams")
public class ExamController {
	@Autowired
    private ExamService examService;
	@Autowired
    private CourseService courseService;

    @GetMapping
    public String listExams(Model model) {
    	List<Exam> exams = examService.getAll();
        model.addAttribute("exams", exams);
        return "exams";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
    	List<Course> courses = courseService.getAllCourses();
    	model.addAttribute("courses", courses);
    	model.addAttribute("exam", new Exam());
    	// Adding Exam lists
        model.addAttribute("exams", examService.getAll());
        return "add-exam";
    }

    @PostMapping
    public String saveExam(@ModelAttribute Exam exam) {
    	Course course = courseService.getCourseById(exam.getCourse().getCid());  // Fetch the Course
        exam.setCourse(course);
        examService.save(exam);
        return "redirect:/exams";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
    	Exam exam = examService.get(id);
    	List<Course> courses = courseService.getAllCourses();
    	model.addAttribute("exam", exam);
    	 model.addAttribute("courses", courses);
        return "edit-exam";
    }

    @PostMapping("{id}/update")
    public String updateExam(@PathVariable Long id, @ModelAttribute Exam exam) {
        exam.setExamId(id);
        examService.save(exam);
        return "redirect:/exams";
    }

    @GetMapping("/delete/{id}")
    public String deleteExam(@PathVariable Long id) {
        examService.delete(id);
        return "redirect:/exams";
    }
}  

