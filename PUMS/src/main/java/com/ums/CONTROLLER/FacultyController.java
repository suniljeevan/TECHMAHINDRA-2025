package com.ums.CONTROLLER;

import com.ums.MODEL.Course;
import com.ums.MODEL.CourseStudentView;
import com.ums.MODEL.Department;
import com.ums.MODEL.Enrollment;
import com.ums.MODEL.Faculty;
import com.ums.MODEL.Student;
import com.ums.REPOSITORY.FacultyRepository;
import com.ums.SERVICE.DepartmentService;
import com.ums.SERVICE.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/faculty/dashboard")
    public String facultyDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("name", userDetails.getUsername());
        return "faculty-dashboard";  // Ensure this is your Thymeleaf template
    }


    @GetMapping("/faculty/profile")
    public String viewProfile() {
        return "faculty-profile"; // Create faculty-profile.html
    }

    @GetMapping("/faculty/courses")
    public String manageCourses() {
        return "faculty-courses"; // Create faculty-courses.html
    }

    @GetMapping("/faculty/students")
    public String viewStudents() {
        return "faculty-students"; // Create faculty-students.html
    }
    @GetMapping
    public String listFaculties(Model model) {
    	 List<Faculty> faculties = facultyService.getAll();
         model.addAttribute("faculties", faculties);
         return "faculties"; // View for listing faculties
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
    	List<Department> departments = departmentService.getAll();
        model.addAttribute("faculty", new Faculty());
        model.addAttribute("departments", departments);
        return "add-faculty"; // View for adding new faculty
    }

    @PostMapping
    public String saveFaculty(@ModelAttribute Faculty faculty) {
    	Department department = departmentService.get(faculty.getDepartment().getDeptId());  // Fetch the department
        faculty.setDepartment(department);  // Set the department in the faculty entity
        facultyService.save(faculty);
        return "redirect:/faculties"; // Redirect to the list of faculties
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
    	Faculty faculty = facultyService.get(id);
        List<Department> departments = departmentService.getAll();
        model.addAttribute("faculty", faculty);
        model.addAttribute("departments", departments);
        return "edit-faculty"; // View for editing faculty
    }

    @PostMapping("{id}/update")
    public String updateFaculty(@PathVariable Long id, @ModelAttribute Faculty faculty) {
        faculty.setFacultyId(id);
        facultyService.save(faculty);
        return "redirect:/faculties"; // Redirect to the list of faculties
    }

    // Delete faculty
    @GetMapping("/delete/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        facultyService.delete(id);
        return "redirect:/faculties"; // Redirect to the list of faculties
    }
    @GetMapping("/{facultyId}/students")
    public String getStudentsByFaculty(@PathVariable Long facultyId, Model model) {
    	Faculty faculty = facultyService.get(facultyId);
        if (faculty == null) {
            model.addAttribute("error", "Faculty not found with ID: " + facultyId);
            return "faculty_student";
        }

        List<CourseStudentView> courseStudentViews = new ArrayList<>();
        // For each course, get students
        List<Course> courses = faculty.getCoursesHandled();
        for (Course course : courses) {
            List<Student> studentsInCourse = new ArrayList<>();
            List<Enrollment> enrollments = course.getEnrollments();
            for (Enrollment enrollment : enrollments) {
                studentsInCourse.add(enrollment.getStudent());
            }
            // Create a custom view for the course and its students
            courseStudentViews.add(new CourseStudentView(course, studentsInCourse));
        }

        model.addAttribute("faculty", faculty);
        model.addAttribute("courseStudentViews", courseStudentViews);

        return "faculty_student"; // This resolves to faculty_students.html
    }
}