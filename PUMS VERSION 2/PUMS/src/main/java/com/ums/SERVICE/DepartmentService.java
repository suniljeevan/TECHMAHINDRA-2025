package com.ums.SERVICE;

import java.util.List;

import com.ums.MODEL.Department;

public interface DepartmentService {
	public List<Department> getAll();
	public void save(Department d);
	public Department get(Long id);
	public void delete(Long id);
}
