package com.ums.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ums.MODEL.Faculty;
import com.ums.REPOSITORY.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService {
	@Autowired
    private FacultyRepository repo;

    public List<Faculty> getAll() { return repo.findAll(); }
    public void save(Faculty faculty) { repo.save(faculty); }
    public Faculty get(Long id) { return repo.findById(id).orElse(null); }
    public void delete(Long id) { repo.deleteById(id); }
}
