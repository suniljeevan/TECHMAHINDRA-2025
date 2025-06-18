package com.ums.REPOSITORY;

import com.ums.MODEL.Enrollment;
import com.ums.MODEL.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	@Query("SELECT e.student FROM Enrollment e WHERE e.course.cid = :courseId")
	List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);
}
