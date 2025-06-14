package com.ams.REPOSITORY;

import com.ams.MODEL.*;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByEmail(String email);
	
	 // ✅ Add this only if using getAllSchools() in controller
    @Query("SELECT DISTINCT s.school FROM Student s WHERE s.school IS NOT NULL")
    List<String> findDistinctSchools();

    // ✅ Add this if filtering students by selected school
    List<Student> findBySchool(String school);
    
    long countByProgramAndStatus(String program, String status);
    
}