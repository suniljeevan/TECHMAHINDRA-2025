package com.techm.rpm.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techm.rpm.MODEL.Admin;
import com.techm.rpm.MODEL.Faculty;
import com.techm.rpm.MODEL.Project;
import com.techm.rpm.SERVICE.ProjectService;
import com.techm.rpm.SERVICE.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProjectController {
	@Autowired
	private ProjectService service;
	@Autowired
	private StudentService studservice;
	
	@GetMapping("/projectlist")
    public String viewProjects(Model model) {
        model.addAttribute("projects", service.getAllProjects());
        return "faculty/new-faculty-projects";
    }
	@GetMapping("/admin-project-list")
    public String viewAllProjects(Model model, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin"); // Store in session
    	
    	model.addAttribute("admin", admin);
        model.addAttribute("projects", service.getAllProjects());
        return "admin/new-admin-projects";
    }
	 
	 @GetMapping("/faculty-project-list")
	 public String viewFacultyProjects( Model model, HttpSession session) {
		 Faculty faculty = (Faculty) session.getAttribute("faculty");
		 int id = faculty.getFacultyId();
		 List<Project> projects = service.fetchFacultyProjects(id);
	     model.addAttribute("projects", projects);
	     model.addAttribute("facultyId", id);
	     model.addAttribute("faculty", faculty);

	     return "admin/new-admin-projects"; // This should map to view.html in the facultyprojects folder
	 }

	
	 @GetMapping("/project/new")
	    public String addForm(Model model, HttpSession session) {
	        Admin admin = (Admin) session.getAttribute("admin");
	        if (admin != null) {
	        	model.addAttribute("project", new Project());
	            model.addAttribute("admin", admin);
	            return "new-add-project";
	        }

	        // Check if faculty is logged in
	        Faculty faculty = (Faculty) session.getAttribute("faculty");
	        if (faculty != null) {
	        	model.addAttribute("project", new Project());
	            model.addAttribute("faculty", faculty);
	            return "new-add-project";
	        }

	        return "redirect:/login";
	    }
	 
	 @PostMapping("/addproject")
	    public String saveProject(@ModelAttribute Project project, Model model, HttpSession session) {
	        service.saveProject(project);
	        Admin admin = (Admin) session.getAttribute("admin");
	        if (admin != null) {
	            model.addAttribute("admin", admin);
	            return "redirect:/admin-project-list";
	        }

	        // Check if faculty is logged in
	        Faculty faculty = (Faculty) session.getAttribute("faculty");
	        if (faculty != null) {
	            model.addAttribute("faculty", faculty);
	            return "redirect:/faculty-project-list";
	        }

	        // If no one is logged in (optional fallback)
	        return "redirect:/login";
	    }
	 @GetMapping("/project/edit/{projectId}")
	    public String editForm(@PathVariable String projectId, Model model, HttpSession session) {
		 Admin admin = (Admin) session.getAttribute("admin");
	        if (admin != null) {
	            model.addAttribute("admin", admin);
	            model.addAttribute("students", studservice.getStudentsInProject(projectId));
		        model.addAttribute("project", service.getProjectById(projectId));
		        return "new-edit-project";
	        }

	        // Check if faculty is logged in
	        Faculty faculty = (Faculty) session.getAttribute("faculty");
	        if (faculty != null) {
	            model.addAttribute("faculty", faculty);
	            model.addAttribute("students", studservice.getStudentsInProject(projectId));
		        model.addAttribute("project", service.getProjectById(projectId));
		        return "new-edit-project";
	        }

	        // If no one is logged in (optional fallback)
	        return "redirect:/login";
	        }
	 @PostMapping("/project/update/{projectId}")
	    public String updateProject(@PathVariable String projectId, @ModelAttribute Project project,Model model, HttpSession session) {
	        project.setProjectId(projectId);
	        service.saveProject(project);
	        Admin admin = (Admin) session.getAttribute("admin");
	        if (admin != null) {
	            model.addAttribute("admin", admin);
	            return "redirect:/admin-project-list";
	        }

	        // Check if faculty is logged in
	        Faculty faculty = (Faculty) session.getAttribute("faculty");
	        if (faculty != null) {
	            model.addAttribute("faculty", faculty);
	            return "redirect:/faculty-project-list";
	        }

	        // If no one is logged in (optional fallback)
	        return "redirect:/login";
	    }
	 
	 @GetMapping("/project/delete/{projectId}")
	    public String deleteProject(@PathVariable String projectId, HttpSession session, Model model) {
	        service.deleteProject(projectId);
	        Admin admin = (Admin) session.getAttribute("admin");
	        if (admin != null) {
	            model.addAttribute("admin", admin);
	            return "redirect:/admin-project-list";
	        }

	        // Check if faculty is logged in
	        Faculty faculty = (Faculty) session.getAttribute("faculty");
	        if (faculty != null) {
	            model.addAttribute("faculty", faculty);
	            return "redirect:/faculty-project-list";
	        }

	        // If no one is logged in (optional fallback)
	        return "redirect:/login";	    }
	

	 
	    
	    @GetMapping("/faculty-dashboard")
	    public String showFacultyDashboard(Model model, HttpSession session) {
	        Faculty faculty = (Faculty) session.getAttribute("faculty");
	    	
	    	model.addAttribute("faculty", faculty);
	    	model.addAttribute("totalProjects", service.getTotalfacultyProjects());
            model.addAttribute("completedProjects", service.getCompletedfacultyProjects());
            model.addAttribute("fundedProjects", service.getFundedfacultyProjects());
            
            double totalAmount = service.getTotalfacultyFundedAmount(); // or getTotalfacultyFundedAmount
            String formattedAmount = "₹" + formatIndianCurrency(totalAmount);
            model.addAttribute("totalFundedAmount", formattedAmount);

            model.addAttribute("countCseProjects", service.countfacultyCseProjects());
	        model.addAttribute("countEceProjects", service.countfacultyEceProjects());
	        model.addAttribute("countEeProjects", service.countfacultyEeProjects());
	        model.addAttribute("countMeProjects", service.countfacultyMeProjects());
	        model.addAttribute("countCeProjects", service.countfacultyCeProjects());
	        model.addAttribute("countInhouseProjects", service.countfacultyInhouseProjects());

	        return "admin/new-admin-dashboard";  // name of the HTML template (without .html)
	    }
	    @GetMapping("/admin-dashboard")
	    public String showAdminDashboard(Model model, HttpSession session) {
	    	Admin admin = (Admin) session.getAttribute("admin"); // Store in session
	    	
	    	model.addAttribute("admin", admin);
	        model.addAttribute("totalProjects", service.getTotalProjects());
	        model.addAttribute("completedProjects", service.getCompletedProjects());
	        model.addAttribute("fundedProjects", service.getFundedProjects());

	        double totalAmount = service.getTotalFundedAmount(); // or getTotalfacultyFundedAmount
            String formattedAmount = "₹" + formatIndianCurrency(totalAmount);
            model.addAttribute("totalFundedAmount", formattedAmount);

	        model.addAttribute("countCseProjects", service.countCseProjects());
	        model.addAttribute("countEceProjects", service.countEceProjects());
	        model.addAttribute("countEeProjects", service.countEeProjects());
	        model.addAttribute("countMeProjects", service.countMeProjects());
	        model.addAttribute("countCeProjects", service.countCeProjects());
	        model.addAttribute("countInhouseProjects", service.countInhouseProjects());
	        return "admin/new-admin-dashboard";  // name of the HTML template (without .html)
	    }
	    
	    private String formatIndianCurrency(double amount) {
	        String amountStr = String.format("%.2f", amount);
	        String[] parts = amountStr.split("\\.");
	        String intPart = parts[0];
	        String decPart = parts[1];

	        // Apply Indian format to the integer part
	        char[] digits = intPart.toCharArray();
	        StringBuilder result = new StringBuilder();

	        int len = digits.length;
	        int count = 0;

	        for (int i = len - 1; i >= 0; i--) {
	            result.append(digits[i]);
	            count++;
	            if (i > 0) {
	                if ((count == 3) || (count > 3 && (count - 3) % 2 == 0)) {
	                    result.append(",");
	                }
	            }
	        }

	        // Reverse to get final formatted value
	        result.reverse().append(".").append(decPart);
	        return result.toString();
	    }
	    
	    @GetMapping("/project/addstudent/{projectId}")
		 public String sendProjectDetails(@PathVariable String projectId, Model model) {
		     Project project = service.getProjectById(projectId);
		     model.addAttribute("project", project);
		     return "add-student-project"; // This is projectDetails.html
		 }

		 @PostMapping("/assignStudentToProject")
		 public String assignStudentToProject(
		         @RequestParam String studentId,
		         @RequestParam String projectId,
		         Model model) {

		     // Call service/repo to update DB
		     int result = studservice.assignProjectToStudent(studentId, projectId);

		     model.addAttribute("message", result > 0 ? "Assigned Successfully" : "Failed to Assign");

		     return "redirect:/projectlist"; // Redirect to some result page or same form
		 }

		    @GetMapping("/removeStudentFromProject/{studentId}")
		    public String removeProject(@PathVariable String studentId, Model model) {
		        int result = studservice.removeStudentFromProject(studentId);
		        model.addAttribute("message", result > 0 ? "Assigned Successfully" : "Failed to Assign");

			     return "redirect:/projectlist";
		    }

}
