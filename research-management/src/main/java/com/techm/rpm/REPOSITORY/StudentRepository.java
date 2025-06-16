package com.techm.rpm.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techm.rpm.MODEL.Student;

import jakarta.transaction.Transactional;

public interface StudentRepository extends JpaRepository<Student, String> {
	List<Student> findByProject_ProjectId(String projectId);

	@Modifying
	@Transactional
	@Query("UPDATE Student s SET s.project.projectId = :projectId WHERE s.studentId = :studentId")
	int assignProjectToStudent(@Param("studentId") String studentId, @Param("projectId") String projectId);
	
	@Modifying
    @Transactional
    @Query("UPDATE Student s SET s.project = null WHERE s.studentId = :studentId")
    int removeStudentFromProject(@Param("studentId") String studentId);

}
