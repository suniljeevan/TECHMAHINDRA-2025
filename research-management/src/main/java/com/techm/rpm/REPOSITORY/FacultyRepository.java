package com.techm.rpm.REPOSITORY;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techm.rpm.MODEL.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
	 Optional<Faculty> findByFacultyEmailAndFacultyPassword(String email, String password);
}
