package com.ums.CONTROLLER;
import com.ums.MODEL.Attendance;
import com.ums.MODEL.Course;
import com.ums.MODEL.Student;
import com.ums.SERVICE.AttendanceService;
import com.ums.SERVICE.CourseService;
import com.ums.SERVICE.CourseServiceImpl;
import com.ums.SERVICE.EnrollmentService;
import com.ums.SERVICE.StudentService;
import com.ums.SERVICE.StudentServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
	@Autowired
	private EnrollmentService enrollmentService;
	@GetMapping
	public String listAttendances(Model model) {
	    List<Course> courses = courseService.getCoursesForToday(); // Write this logic to return today's courses
	    for (Course course : courses) {
            boolean isAttendanceSubmitted = course.getAttendanceList().stream()
                .anyMatch(a -> a.getDate().equals(LocalDate.now().toString())&& "Submitted".equals(a.getSubmissionStatus()));
            // Add the flag to each course
            course.setAttendanceSubmitted(isAttendanceSubmitted);
        }
	    model.addAttribute("courses", courses);
	    LocalDate today = LocalDate.now();
	    model.addAttribute("today", today.toString()); 
	    return "attendances";
	}

    @GetMapping("/add")
    public String showCreateForm(@RequestParam Long courseId, Model model) {
    	 Course course = courseService.getCourseById(courseId);
    	    List<Student> students = enrollmentService.getEnrolledStudents(courseId);
    	    model.addAttribute("course", course);
    	    model.addAttribute("students", students);
    	    return "add-attendance";
    }
    @PostMapping("/save")
    public String storeAttendance(@RequestParam Long courseId,
                                 @RequestParam List<Long> studentIds,
                                 @RequestParam List<String> statuses) {

        Course course = courseService.getCourseById(courseId);
        String today = LocalDate.now().toString();

        for (int i = 0; i < studentIds.size(); i++) {
            Student student = studentService.getStudentById(studentIds.get(i));
            String status = statuses.get(i);

            Attendance attendance = new Attendance();
            attendance.setStudent(student);
            attendance.setCourse(course);
            attendance.setDate(today);
            attendance.setStatus(status);
            attendance.setSubmissionStatus("Submitted");

            attendanceService.save(attendance);
        }

        return "redirect:/attendances";
    }

    @PostMapping
    public String saveAttendance(@ModelAttribute Attendance attendance) {
    	attendanceService.save(attendance);
        return "redirect:/attendances";
    }

    @GetMapping("/attendances/edit")
    public String showEditAttendanceForm(@RequestParam Long courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        String today = LocalDate.now().toString();

        List<Attendance> attendances = attendanceService.getByCourseAndDate(courseId, today);
        model.addAttribute("course", course);
        model.addAttribute("attendances", attendances);
        return "edit-attendance";
    }


    @PostMapping("/update")
    public String updateAttendance(@RequestParam List<Long> attendanceIds,
                                   @RequestParam List<String> statuses) {

        for (int i = 0; i < attendanceIds.size(); i++) {
            Attendance att = attendanceService.get(attendanceIds.get(i));
            att.setStatus(statuses.get(i));
            attendanceService.save(att);
        }

        return "redirect:/attendances";
    }

    @GetMapping("/delete")
    public String deleteAttendanceByCourseAndDate(@RequestParam Long courseId) {
        String today = LocalDate.now().toString();
        attendanceService.deleteByCourseAndDate(courseId, today);
        return "redirect:/attendances";
    }
}  

