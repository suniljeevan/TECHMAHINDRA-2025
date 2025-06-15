package com.ums.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ums.MODEL.Evaluation;
import com.ums.REPOSITORY.EvaluationRepository;

@Service
public class EvaluationServiceImpl implements EvaluationService {
	@Autowired
    private EvaluationRepository repo;
    public List<Evaluation> getAll() { return repo.findAll(); }
    public void save(Evaluation r) { repo.save(r); }
    public Evaluation get(Long id) { return repo.findById(id).orElse(null); }
    public void delete(Long id) { repo.deleteById(id); }
}
