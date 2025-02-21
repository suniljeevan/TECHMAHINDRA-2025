package Controller;

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

}
