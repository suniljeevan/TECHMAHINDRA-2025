package com.admin.scholarship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.scholarship.model.Scholarship;
import com.admin.scholarship.repository.ScholarshipRepository;
@Service
public class ScholarshipService {
    @Autowired
    private ScholarshipRepository repo;

    public List<Scholarship> getAllScholarships() {
        return repo.findAll();
    }

    public void saveScholarship(Scholarship s) {
        repo.save(s);
    }

    public void updateScholarship(Long id, Scholarship updated) {
        Scholarship existing = repo.findById(id).orElseThrow();
        existing.setTitle(updated.getTitle());
        existing.setAmount(updated.getAmount());
        existing.setEgc1(updated.getEgc1());
        existing.setEgc2(updated.getEgc2());
        existing.setEgc3(updated.getEgc3());
        existing.setEgc4(updated.getEgc4());
        existing.setEgc5(updated.getEgc5());
        repo.save(existing);
    }
    public void deleteScholarship(Long id) {
        repo.deleteById(id);
    }

}

