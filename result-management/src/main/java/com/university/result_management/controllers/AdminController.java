package com.university.result_management.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.university.result_management.models.Course;
import com.university.result_management.models.Department;
import com.university.result_management.models.Instructor;
import com.university.result_management.models.Student;
import com.university.result_management.repositories.CourseRepository;
import com.university.result_management.repositories.DepartmentRepository;
import com.university.result_management.repositories.InstructorRepository;
import com.university.result_management.repositories.StudentRepository;
import com.university.result_management.services.AdminService;
import com.university.result_management.services.CourseService;
import com.university.result_management.services.DepartmentService;
import com.university.result_management.services.InstructorService;
import com.university.result_management.services.StudentService;
import com.university.result_management.services.SystemSettingService;
import com.university.result_management.services.UploadService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DepartmentService departmentService;
    private final StudentService studentService;
    private final AdminService adminService;
    private final CourseService courseService;
    private final InstructorService instructorService;
    private final SystemSettingService settingService;
    private final UploadService uploadService;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final InstructorRepository instructorRepository;

    // --------------------------- Admin Dashboard ---------------------------

    //Admin Dashboard Page
    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request,Model model) {
    	model.addAttribute("requestURI", request.getRequestURI());
        return "html/admin/admin-dashboard"; // Resolves to dashboard.jsp
    }

    // --------------------------- Student sec ---------------------------

    // Show Create Student Page
    @GetMapping("/create-student")
    public String showCreateStudentPage(Model model) {
        List<Department> departments = departmentService.getAllDepartment();
        model.addAttribute("departments", departments);
        model.addAttribute("student", new Student());
        return "html/admin/create-student"; // Resolves to create-student.jsp
    }

    // Create Student - Save Student Data
    @PostMapping("/create-student")
  
    public String createStudent(@ModelAttribute Student student, 
            @RequestParam("departmentId") Long departmentId) {
    	 // Simulate a long-running operation using Thread.sleep()
     
        Department department = departmentService.getDepartmentById(departmentId);
        student.setDepartment(department);
        adminService.createStudent(student);
        System.out.println("-----------------Student saved successfully!");
        return "redirect:/admin/students";
    }

    // Show Delete Student Confirmation Page
    @GetMapping("/delete-student/{id}")
    public String showDeleteStudentConfirmation(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "html/admin/delete-student"; // Confirmation page for deletion
    }

    // Delete Student - Perform Deletion
    @PostMapping("/delete-student")
    public String deleteStudent(@RequestParam Long id) {
        adminService.deleteStudent(id);
        return "redirect:/admin/students";
    }

    // Show Edit Student Form
    @GetMapping("/edit-student/{id}")
    public String showEditStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentProfile(id);
        List<Department> departments = departmentService.getAllDepartment();
        model.addAttribute("departments", departments);
        model.addAttribute("student", student);
        return "html/admin/edit-student"; // edit-student.jsp
    }

    // Update Student - Save Edited Data
    @PostMapping("/edit-student")
    public String updateStudent(@ModelAttribute Student student, @RequestParam("departmentId") Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        student.setDepartment(department);
        adminService.updateStudent(student);
        return "redirect:/admin/students";
    }

    // List All Students
    @GetMapping("/students")
    public String showStudents(Model model) {
    	 try {
             List<Student> students = studentService.getAllStudents();
             model.addAttribute("allstudents", students);
             return "html/admin/list-students";
         } catch (Exception e) {
             return handleError(model, "Error Fetching Students", e.getMessage(), "/admin/dashboard");
         }
    }
    
    //------------upload bulk---------------------------------
    @GetMapping("/upload")
    public String showUploadData() {
    	return "html/admin/upload-data";
    }
    @PostMapping("/upload/preview")
    public String handleUpload(@RequestParam("dataType") String dataType,
                               @RequestParam("file") MultipartFile file,
                               Model model,
                               HttpSession session) {
        try {
            switch (dataType.toLowerCase()) {
                case "student":
                    List<Student> students = uploadService.parseStudents(file);
                    session.setAttribute("previewStudents", students);
                    model.addAttribute("students", students);
                    return "html/admin/preview-students";

                case "instructor":
                    List<Instructor> instructors = uploadService.parseInstructors(file);
                    session.setAttribute("previewInstructors", instructors);
                    model.addAttribute("instructors", instructors);
                    return "html/admin/preview-instructors";

                case "department":
                    List<Department> departments = uploadService.parseDepartments(file);
                    session.setAttribute("previewDepartments", departments);
                    model.addAttribute("departments", departments);
                    return "html/admin/preview-departments";

                case "course":
                    List<Course> courses = uploadService.parseCourses(file);
                    session.setAttribute("previewCourses", courses);
                    model.addAttribute("courses", courses);
                    return "html/admin/preview-courses";

                default:
                    throw new IllegalArgumentException("Unsupported data type.");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Failed to process upload: " + e.getMessage());
            return "html/common/message";
        }
    }
    @PostMapping("/upload/save/students")
    public String saveStudents(HttpSession session, Model model) {
        List<Student> students = (List<Student>) session.getAttribute("previewStudents");
        studentRepository.saveAll(students);
        session.removeAttribute("previewStudents");
        model.addAttribute("message", "Students uploaded successfully.");
        return "html/common/message";
    }
    @PostMapping("/upload/save/instructors")
    public String saveInstructors(HttpSession session, Model model) {
        List<Instructor> instructors = (List<Instructor>) session.getAttribute("previewInstructors");
        instructorRepository.saveAll(instructors);
        session.removeAttribute("previewInstructors");
        model.addAttribute("message", "Instructors uploaded successfully.");
        model.addAttribute("backUrl", "/admin/instructors");
        return "html/common/message";
    }

    @PostMapping("/upload/save/departments")
    public String saveDepartments(HttpSession session, Model model) {
        List<Department> departments = (List<Department>) session.getAttribute("previewDepartments");
        departmentRepository.saveAll(departments);
        session.removeAttribute("previewDepartments");
        model.addAttribute("message", "Departments uploaded successfully.");
        model.addAttribute("backUrl", "/admin/departments");
        return "html/common/message";
    }

    @PostMapping("/upload/save/courses")
    public String saveCourses(HttpSession session, Model model) {
        List<Course> courses = (List<Course>) session.getAttribute("previewCourses");
        courseRepository.saveAll(courses);
        session.removeAttribute("previewCourses");
        model.addAttribute("message", "Courses uploaded successfully.");
        model.addAttribute("backUrl", "/admin/courses");
        return "html/common/message";
    }




    // --------------------------- Instructor sec ---------------------------

    // Show Create Instructor Page
    @GetMapping("/create-instructor")
    public String showCreateInstructorPage(Model model) {
        List<Department> departments = departmentService.getAllDepartment();
        model.addAttribute("departments", departments);
        model.addAttribute("instructor", new Instructor());
        return "html/admin/create-instructor";
    }

    // Create Instructor - Save Instructor Data
    @PostMapping("/create-instructor")
    public String createInstructor(@ModelAttribute Instructor instructor,
            @RequestParam("departmentId") Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        instructor.setDepartment(department);
        adminService.createInstructor(instructor);
        System.out.println("-----------------instructor saved successfully!");
        return "redirect:/admin/instructors";
    }

    // Show Delete Instructor Confirmation Page
    @GetMapping("/delete-instructor/{id}")
    public String showDeleteInstructorConfirmation(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "html/admin/delete-instructor"; // Confirmation page for deletion
    }

    // Delete Instructor - Perform Deletion
    @PostMapping("/delete-instructor")
    public String deleteinstructor(@RequestParam Long id) {
        adminService.deleteInstructor(id);
        return "redirect:/admin/instructors";
    }

    // Show Edit Instructor Form
    @GetMapping("/edit-instructor/{id}")
    public String showEditInstructorForm(@PathVariable Long id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id);
        List<Department> departments = departmentService.getAllDepartment();
        model.addAttribute("departments", departments);
        model.addAttribute("instructor", instructor);
        return "html/admin/edit-instructor";
    }

    // Update Instructor - Save Edited Data
    @PostMapping("/edit-instructor")
    public String updateInstructor(@ModelAttribute Instructor instructor, @RequestParam("departmentId") Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        instructor.setDepartment(department);
        adminService.updateInstructor(instructor);
        return "redirect:/admin/instructors";
    }

    // List All Instructors
    @GetMapping("/instructors")
   
    	public String viewInstructors(Model model) {
            try {
                List<Instructor> instructors = instructorService.getAllInstructor();
                model.addAttribute("allinstructors", instructors);
                return "html/admin/list-instructors";
            } catch (Exception e) {
                return handleError(model, "Error Fetching Instructors", e.getMessage(), "/admin/dashboard");
            }
    }

    // --------------------------- Department sec ---------------------------

    // Show Create Department Page
    @GetMapping("/create-department")
    public String showCreateDepartmentPage(Model model) {
        model.addAttribute("department", new Department());
        return "html/admin/create-department";
    }

    // Create Department - Save Department Data
    @PostMapping("/create-department")
    public String createDepartment(@ModelAttribute Department department) {
        adminService.createDepartment(department);
        return "redirect:/admin/departments";
    }

    // Show Delete Department Confirmation Page
    @GetMapping("/delete-department/{id}")
    public String showDeleteDepartmentConfirmation(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "html/admin/delete-department"; 
    }

    // Delete Department - Perform Deletion
    @PostMapping("/delete-department")
    public String deletedepartment(@RequestParam Long id) {
        adminService.deleteDepartment(id);
        return "redirect:/admin/departments";
    }

    // Show Edit Department Form
    @GetMapping("/edit-department/{id}")
    public String showEditDepartmentForm(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "html/admin/edit-department";
    }

    // Update Department - Save Edited Data
    @PostMapping("/edit-department")
    public String updateDepartment(@ModelAttribute Department department) {
        adminService.updateDepartment(department);
        return "redirect:/admin/departments";
    }

    // List All Departments
    @GetMapping("/departments")
    public String showDepartments(Model model) {
    	 try {
             List<Department> departments = departmentService.getAllDepartment();
             model.addAttribute("alldepartments", departments);
             return "html/admin/list-departments";
         } catch (Exception e) {
             return handleError(model, "Error Fetching Departments", e.getMessage(), "/admin/dashboard");
         }
    }

    // --------------------------- Course sec ---------------------------

    // Show Create Course Page
    @GetMapping("/create-course")
    public String createCourse(Model model) {
        List<Department> departments = departmentService.getAllDepartment();
        model.addAttribute("departments", departments);
        List<Instructor> instructors = instructorService.getAllInstructor();
        model.addAttribute("instructorss", instructors);
        model.addAttribute("course", new Course());
        return "html/admin/create-course";
    }

    // Create Course - Save Course Data
    @PostMapping("/create-course")
    public String createCourse(@ModelAttribute Course course,
            @RequestParam("departmentId") Long departmentId,
            @RequestParam("instructorId") Long instructorId) {
        Department departmentID = departmentService.getDepartmentById(departmentId);
        course.setDepartment(departmentID);
        Instructor instructorID = instructorService.getInstructorById(instructorId);
        course.setInstructor(instructorID);
        adminService.createCourse(course);
        return "redirect:/admin/courses";
    }

    // Show Delete Course Confirmation Page
    @GetMapping("/delete-course/{id}")
    public String showDeleteCourseConfirmation(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "html/admin/delete-course";
    }

    // Delete Course - Perform Deletion
    @PostMapping("/delete-course")
    public String deletecourse(@RequestParam Long id) {
        adminService.deleteCourse(id);
        return "redirect:/admin/courses";
    }

    // Show Edit Course Form
    @GetMapping("/edit-course/{id}")
    public String showEditCourseForm(@PathVariable Long id, Model model) {
        Course course = courseService.getOneCourse(id);
        List<Department> departments = departmentService.getAllDepartment();
        List<Instructor> instructors = instructorService.getAllInstructor();
        model.addAttribute("departments", departments);
        model.addAttribute("instructorss", instructors);
        model.addAttribute("course", course);
        return "html/admin/edit-course";
    }

    // Update Course - Save Edited Data
    @PostMapping("/edit-course")
    public String updateCourse(@ModelAttribute Course course,
            @RequestParam("departmentId") Long departmentId,
            @RequestParam("instructorId") Long instructorId) {
        Department department = departmentService.getDepartmentById(departmentId);
        course.setDepartment(department);
        Instructor instructor = instructorService.getInstructorById(instructorId);
        course.setInstructor(instructor);
        adminService.updateCourse(course);
        return "redirect:/admin/courses";
    }

    // List All Courses
    @GetMapping("/courses")
    public String showCourses(Model model) {
        model.addAttribute("allcourses", courseService.getAllCourse());
        return "html/admin/list-courses";
    }

    // --------------------------- Additional  ---------------------------

    // Get All Instructors by Department (for dynamic drop down)
    @GetMapping("/instructors/department/{departmentId}")
    @ResponseBody
    public List<Instructor> getInstructorsByDepartment(@PathVariable Long departmentId) {
        return instructorService.getInstructorByDepartment(departmentId);
    }
    @GetMapping("/settings")
    public String settingsPage(Model model) {
        model.addAttribute("settings", settingService.getSettings());
        return "html/admin/admin-settings";
    }

    @PostMapping("/settings/update")
    public String updateSettings(@RequestParam(value = "enrollCourseEnabled", defaultValue = "false") boolean enroll,
                                 @RequestParam(value = "assignMarksEnabled", defaultValue = "false") boolean assign) {
        settingService.updateSettings(enroll, assign);
        return "redirect:/admin/admin-settings";
    }
    @PostMapping("/settings/increment-semester")
    public String incrementSemester() {
        studentService.incrementAllSemesters();
        return "redirect:/admin/settings?success=Semester+incremented+for+all+students";
    }

    @PostMapping("/settings/decrement-semester")
    public String decrementSemester() {
        studentService.decrementAllSemesters();
        return "redirect:/admin/settings?success=Semester+decremented+for+all+students";
    }
    private String handleError(Model model, String title, String message, String backUrl) {
        model.addAttribute("title", title);
        model.addAttribute("message", message);
        model.addAttribute("backUrl", backUrl);
        return "html/common/message";
    }
}
