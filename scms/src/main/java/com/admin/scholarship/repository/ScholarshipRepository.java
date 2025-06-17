package com.admin.scholarship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.admin.scholarship.model.Scholarship;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
	
	Scholarship findByTitle(String title);

}
