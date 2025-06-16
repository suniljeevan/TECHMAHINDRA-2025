package com.ttms.service;

import java.util.List;

import com.ttms.model.StudentUser;

public interface StudentService {

	public String upsert(StudentUser studentUser);
    
    public StudentUser getStudentById(Integer studentid);
    
    public List<StudentUser> getAllStudents();

    public StudentUser findByEmailAndPassword(String email, String password);
    
    public String deleteById(Integer studentid);
}
