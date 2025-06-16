package com.ttms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttms.model.StudentUser;

public interface StudentRepository extends JpaRepository<StudentUser, Integer>  {

	public StudentUser findByStudentEmailAndStudentPassword(String email, String password);
}
