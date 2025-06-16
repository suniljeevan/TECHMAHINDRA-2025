package com.ttms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ttms.model.StudentUser;
import com.ttms.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentRepository studentRepo;

    @Override
    public String upsert(StudentUser studentUser) {
    	studentRepo.save(studentUser);
        return "Success";
    }    

    @Override
    public StudentUser getStudentById(Integer studentid) {
        return studentRepo.findById(studentid).orElse(null);
    }
    
    @Override
    public List<StudentUser> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public StudentUser findByEmailAndPassword(String email, String password) {
        return studentRepo.findByStudentEmailAndStudentPassword(email, password);
    }
    
    public String deleteById(Integer studentId) {
		if(studentRepo.existsById(studentId)) {
			studentRepo.deleteById(studentId);
			return "Deletion success";
		} else {
			return "No Data found";
		}
	}
}
