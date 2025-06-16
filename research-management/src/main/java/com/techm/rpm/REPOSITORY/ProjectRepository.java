package com.techm.rpm.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techm.rpm.MODEL.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {

	List<Project> findByFaculty_FacultyId(int facultyId);

	@Query("SELECT COUNT(p) FROM Project p")
	long getTotalProjects();

	@Query("SELECT COUNT(p) FROM Project p WHERE p.status = 'Completed'")
	long getCompletedProjects();

	@Query("SELECT COUNT(p) FROM Project p WHERE p.projectType = 'Funded'")
	long getFundedProjects();

	@Query("SELECT SUM(p.fundedAmount) FROM Project p WHERE p.projectType = 'Funded'")
	Double getTotalFundedAmount();

	@Query("SELECT COUNT(p) FROM Project p WHERE p.department = 'CSE'")
	long countCseProjects();

	@Query("SELECT COUNT(p) FROM Project p WHERE p.department = 'ECE'")
	long countEceProjects();

	@Query("SELECT COUNT(p) FROM Project p WHERE p.department = 'EE'")
	long countEeProjects();

	@Query("SELECT COUNT(p) FROM Project p WHERE p.department = 'ME'")
	long countMeProjects();

	@Query("SELECT COUNT(p) FROM Project p WHERE p.department = 'CE'")
	long countCeProjects();

	@Query(value = "SELECT COUNT(*) FROM researchproject WHERE LOWER(projectType) = 'inhouse'", nativeQuery = true)
	long countInhouseProjects();
	
	// Total projects by faculty
	@Query("SELECT COUNT(p) FROM Project p WHERE p.faculty.facultyId = :facultyId")
	long getTotalProjectsByFaculty(@Param("facultyId") int facultyId);

	// Completed projects by faculty
	@Query("SELECT COUNT(p) FROM Project p WHERE p.status = 'Completed' AND p.faculty.facultyId = :facultyId")
	long getCompletedProjectsByFaculty(@Param("facultyId") int facultyId);

	// Funded projects by faculty
	@Query("SELECT COUNT(p) FROM Project p WHERE p.projectType = 'Funded' AND p.faculty.facultyId = :facultyId")
	long getFundedProjectsByFaculty(@Param("facultyId") int facultyId);

	// Total funded amount by faculty
	@Query("SELECT SUM(p.fundedAmount) FROM Project p WHERE p.projectType = 'Funded' AND p.faculty.facultyId = :facultyId")
	Double getTotalFundedAmountByFaculty(@Param("facultyId") int facultyId);

	// CSE projects by faculty
	@Query("SELECT COUNT(p) FROM Project p WHERE p.department = 'CSE' AND p.faculty.facultyId = :facultyId")
	long countCseProjectsByFaculty(@Param("facultyId") int facultyId);

	// ECE projects by faculty
	@Query("SELECT COUNT(p) FROM Project p WHERE p.department = 'ECE' AND p.faculty.facultyId = :facultyId")
	long countEceProjectsByFaculty(@Param("facultyId") int facultyId);

	// EE projects by faculty
	@Query("SELECT COUNT(p) FROM Project p WHERE p.department = 'EE' AND p.faculty.facultyId = :facultyId")
	long countEeProjectsByFaculty(@Param("facultyId") int facultyId);

	// ME projects by faculty
	@Query("SELECT COUNT(p) FROM Project p WHERE p.department = 'ME' AND p.faculty.facultyId = :facultyId")
	long countMeProjectsByFaculty(@Param("facultyId") int facultyId);

	// CE projects by faculty
	@Query("SELECT COUNT(p) FROM Project p WHERE p.department = 'CE' AND p.faculty.facultyId = :facultyId")
	long countCeProjectsByFaculty(@Param("facultyId") int facultyId);

	// Inhouse projects by faculty (Native query)
	@Query(value = "SELECT COUNT(*) FROM researchproject WHERE LOWER(projectType) = 'inhouse' AND facultyId = :facultyId", nativeQuery = true)
	long countInhouseProjectsByFaculty(@Param("facultyId") int facultyId);



}
