package com.university.result_management.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.university.result_management.models.Department;

import com.university.result_management.repositories.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {
	private final DepartmentRepository departmentrepo;
	 public List<Department> getAllDepartment() {
		 
	        return departmentrepo.findAll();
	    }
	 public Department getDepartmentById(Long id) {
		    return departmentrepo.findById(id)
		            .orElseThrow(() -> new RuntimeException("Department not found"));
		}

}
