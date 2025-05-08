package com.ums.SERVICE;

import java.util.List;

import com.ums.MODEL.Evaluation;

public interface EvaluationService {
	public List<Evaluation> getAll() ;
    public void save(Evaluation r) ;
    public Evaluation get(Long id) ;
    public void delete(Long id) ;
}
