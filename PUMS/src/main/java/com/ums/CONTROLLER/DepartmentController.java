package com.ums.CONTROLLER;
import com.ums.MODEL.Attendance;
import com.ums.MODEL.Department;
import com.ums.SERVICE.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/{entityPath}")
public class DepartmentController {
	@Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String listDepartments(Model model) {
    	model.addAttribute("entityName","Department");
    	model.addAttribute("entityPath","departments");
    	model.addAttribute("fields",List.of("name","hod","description"));
    	model.addAttribute("idField", "deptId"); 
        model.addAttribute("items", departmentService.getAll());
        return "list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
    	model.addAttribute("entityName", "Department");
        model.addAttribute("entityPath", "departments");
        model.addAttribute("fields", List.of("name", "hod", "description"));
        model.addAttribute("object", new Department());
        return "create";
    }

    @PostMapping
    public String saveDepartment(@ModelAttribute Department department) {
        departmentService.save(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
    	Department department = departmentService.get(id);
    	model.addAttribute("entityName", "Department");
        model.addAttribute("entityPath", "departments");
        model.addAttribute("fields", List.of("name", "hod", "description"));
        model.addAttribute("idField", "deptId");
        model.addAttribute("object", department);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateDepartment(@PathVariable String entityPath,@PathVariable Long id, @ModelAttribute Department department) {
        department.setDeptId(id);
        departmentService.save(department);
        return "redirect:/${entityPath}";
    }

    @PostMapping("{id}/delete")
    public String deleteDepartment(@PathVariable String entityPath,@PathVariable Long id) {
        departmentService.delete(id);
        return "redirect:/${entityPath}";
    }
}  

