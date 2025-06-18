package SERVICE;

import java.util.List;

import MODEL.Student;
import Repository.StudentRepository;

public class StudentServiceImpl implements StudentService{
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
