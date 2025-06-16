package com.ttms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttms.model.FacultyUser;
import com.ttms.repository.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService {
    
    @Autowired
    private FacultyRepository facultyRepo;

    @Override
    public String upsert(FacultyUser facultyUser) {
    	facultyRepo.save(facultyUser);
        return "Success";
    }    

    @Override
    public FacultyUser getFacultyById(Integer facultyid) {
        return facultyRepo.findById(facultyid).orElse(null);
    }
    
    @Override
    public List<FacultyUser> getAllFacultyUsers() {
        return facultyRepo.findAll();
    }

    @Override
    public FacultyUser findByEmailAndPassword(String email, String password) {
        return facultyRepo.findByFacultyEmailAndFacultyPassword(email, password);
    }
    
    public String deleteById(Integer facultyid) {
		if(facultyRepo.existsById(facultyid)) {
			facultyRepo.deleteById(facultyid);
			return "Deletion success";
		} else {
			return "No Data found";
		}
	}
}