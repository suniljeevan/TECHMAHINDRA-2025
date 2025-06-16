package com.techm.rpm.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.rpm.MODEL.Student;
import com.techm.rpm.REPOSITORY.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository repo;
	
	public List<Student> getStudentsInProject(String projectId) {
        return repo.findByProject_ProjectId(projectId);
    }
	public int assignProjectToStudent(String studentId, String projectId) {
        return repo.assignProjectToStudent(studentId, projectId);
    }
	public int removeStudentFromProject(String studentId) {
        return repo.removeStudentFromProject(studentId);
    }

}
