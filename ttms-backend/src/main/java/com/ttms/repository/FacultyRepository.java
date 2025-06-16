package com.ttms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttms.model.FacultyUser;

public interface FacultyRepository extends JpaRepository<FacultyUser, Integer> {

	public FacultyUser findByFacultyEmailAndFacultyPassword(String email, String password);

}