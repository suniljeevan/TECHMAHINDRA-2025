package com.university.result_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.university.result_management.models.Admin;
import com.university.result_management.models.Course;
import com.university.result_management.models.Department;
import com.university.result_management.models.Instructor;
import com.university.result_management.models.Student;
import com.university.result_management.repositories.AdminRepository;
import com.university.result_management.repositories.CourseRepository;
import com.university.result_management.repositories.DepartmentRepository;
import com.university.result_management.repositories.InstructorRepository;
import com.university.result_management.repositories.StudentRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AdminService {

    private final StudentRepository studentRepo;
    private final InstructorRepository instructorRepo;
    private final AdminRepository adminRepo;
    private final CourseRepository courseRepo;
    private final DepartmentRepository deptRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public Student createStudent(Student student) {
        // Optionally store student login info separately
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepo.save(student);
    }
    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }
   
    public Instructor createInstructor(Instructor instructor) {
        instructor.setPassword(passwordEncoder.encode(instructor.getPassword()));
        return instructorRepo.save(instructor);
    }
    public void deleteInstructor(Long id) {
        instructorRepo.deleteById(id);
    }
    

   
    public Department createDepartment(Department dept) {
        return deptRepo.save(dept);
    }
    public void deleteDepartment(Long id) {
        deptRepo.deleteById(id);
    }
    public Course createCourse(Course course) {
		return courseRepo.save(course);
		
	}
    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepo.findAll();
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
    public Student updateStudent(Student student) { 
    	return studentRepo.save(student); 
    }
    public Instructor updateInstructor(Instructor instructor) { 
    	return instructorRepo.save(instructor);
    }
    public Department updateDepartment(Department department) { 
    	return deptRepo.save(department); 
    }
    public Course updateCourse(Course course) { 
    	return courseRepo.save(course);
    }

    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepo.save(admin);
    }

    @PostConstruct
    public void initAdmin() {
    	if (adminRepo.findByUsername("admin1").isEmpty()) {
    	    createDefaultAdmin();
    	} else {
            System.out.println("ℹ️ Default admin already exists.");
        }
    }

    private void createDefaultAdmin() {
        Admin admin = new Admin();
        admin.setUsername("admin1");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole("ROLE_ADMIN");
        adminRepo.save(admin);
    }

	



	
}
