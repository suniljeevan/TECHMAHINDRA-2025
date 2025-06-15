package com.ums.CONTROLLER;

import com.ums.MODEL.Course;
import com.ums.MODEL.Department;
import com.ums.MODEL.Faculty;
import com.ums.SERVICE.DepartmentService;
import com.ums.SERVICE.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private DepartmentService departmentService;

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
}