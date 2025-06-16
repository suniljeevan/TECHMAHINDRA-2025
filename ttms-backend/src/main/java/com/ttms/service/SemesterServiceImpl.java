package com.ttms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttms.model.Semester;
import com.ttms.repository.SemesterRepository;

@Service
public class SemesterServiceImpl implements SemesterService{
	
	@Autowired
	private SemesterRepository semesterRepo;
	
	public String upsert(Semester semester) {
		semesterRepo.save(semester);
		return "Success";
	}
	
	public Semester getById(Integer sid) {
		Optional<Semester> findById= semesterRepo.findById(sid);
		
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
	
	public List<Semester> getAllSemesters() {
		return semesterRepo.findAll();
	}
	
	public String deleteById(Integer sid) {
		if(semesterRepo.existsById(sid)) {
			semesterRepo.deleteById(sid);
			return "Deletion success";
		} else {
			return "No Data found";
		}
	}
}
