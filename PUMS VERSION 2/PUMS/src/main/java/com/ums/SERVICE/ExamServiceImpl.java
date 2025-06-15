package com.ums.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ums.MODEL.Exam;
import com.ums.REPOSITORY.ExamRepository;

@Service
public class ExamServiceImpl implements ExamService {
	@Autowired
    private ExamRepository repo;
    public List<Exam> getAll() { return repo.findAll(); }
    public void save(Exam e) { repo.save(e); }
    public Exam get(Long id) { return repo.findById(id).orElse(null); }
    public void delete(Long id) { repo.deleteById(id); }
}
