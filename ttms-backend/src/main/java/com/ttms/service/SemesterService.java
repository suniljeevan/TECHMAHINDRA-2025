package com.ttms.service;

import java.util.List;

import com.ttms.model.Semester;

public interface SemesterService {
	
	public String upsert(Semester semester);
	
	public Semester getById(Integer sid);
	
	public List<Semester> getAllSemesters();
	
	public String deleteById(Integer sid);
}
