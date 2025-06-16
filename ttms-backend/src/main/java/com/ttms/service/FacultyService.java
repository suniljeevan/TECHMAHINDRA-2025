package com.ttms.service;

import java.util.List;
import com.ttms.model.FacultyUser;

public interface FacultyService {
	
public String upsert(FacultyUser facultyUser);
    
    public FacultyUser getFacultyById(Integer facultyid);
    
    public List<FacultyUser> getAllFacultyUsers();

    // New method for checking email and password
    public FacultyUser findByEmailAndPassword(String email, String password);
    
    public String deleteById(Integer facultyid);

}
