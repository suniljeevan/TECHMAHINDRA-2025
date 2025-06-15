package com.ums.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ums.MODEL.Department;
import com.ums.REPOSITORY.DepartmentRepository;
@Service
public class DepartmentServiceImpl implements DepartmentService {
	 @Autowired
	    private DepartmentRepository repo;
	    public List<Department> getAll() { return repo.findAll(); }
	    public void save(Department d) { repo.save(d); }
	    public Department get(Long id) { return repo.findById(id).orElse(null); }
	    public void delete(Long id) { repo.deleteById(id); }
}
