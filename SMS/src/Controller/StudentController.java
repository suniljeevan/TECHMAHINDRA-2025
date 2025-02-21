package Controller;

import java.util.List;


import MODEL.Student;
import SERVICE.StudentServiceImpl;
//reuest-response
public class StudentController {
	private StudentServiceImpl studentservice;

	public StudentController(StudentServiceImpl studentservice) {
		this.studentservice = studentservice;
	}
	public int insertStudent(Student s) {
		return studentservice.insertStudent(s);
	}
	
	//
}







