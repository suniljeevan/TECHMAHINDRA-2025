package org.cms.SERVICES;

import java.util.List;

import org.cms.MODEL.Student;
import org.cms.REPOSITORY.StudentRepository;

public class StudentServiceImpl implements StudentService{
	//Explicit Wiring/ Inject
	private StudentRepository repository;
	
	public StudentServiceImpl() {
		super();
	}
	public StudentServiceImpl(StudentRepository repository) {
		this.repository = repository;
	}
	public int insertStudent(Student s) {
		return repository.insertStudent(s);
	}
	public int updateStudent(Student s, String id) {
		return repository.updateStudent(s, id);
	}
	public int deleteStudent(String s) {
		return repository.deleteStudent(s);
	}
	public List<Student> fetchAll() {
		return repository.fetchAllStudents();
	}
	public Student fetchOneStudent(String s) {
		return null;
	}
}
