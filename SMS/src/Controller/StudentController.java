package Controller;

import java.util.List;

import MODEL.Student;
import SERVICE.StudentServiceImpl;

public class StudentController {
	private StudentServiceImpl service;

	public StudentController(StudentServiceImpl service) {
		super();
		this.service = service;
	}
	public int insertStudent(Student s) {
		return service.insertStudent(s);
	}
	public int updateStudent(Student s) {
		return service.insertStudent(s);
	}
	public List<Student> fetchALlStudent() {
		return service.fetchAll();
	}
	
	
	

}
