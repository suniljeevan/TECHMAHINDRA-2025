package com.admin.scholarship.repository;

import com.admin.scholarship.model.Scholarship;
import com.admin.scholarship.model.Student;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student findByName(String username);
	Student findByEmail(String email);
	int countByStatus(Student.Status status);
	@Query("SELECT SUM(s.amount) FROM Student s")
	Double getTotalSanctionedAmount();


	
}
