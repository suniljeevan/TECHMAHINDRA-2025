package com.ums.SERVICE;

import java.util.List;

import com.ums.MODEL.Exam;

public interface ExamService {
	public List<Exam> getAll() ; 
    public void save(Exam e); 
    public Exam get(Long id);
    public void delete(Long id); 
}
