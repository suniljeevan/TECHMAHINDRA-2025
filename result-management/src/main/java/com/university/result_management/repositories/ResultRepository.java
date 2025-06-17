package com.university.result_management.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.university.result_management.models.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByStudentId(Long studentId);
   
    @Query("SELECT MAX(r.totalCredits) FROM Result r WHERE r.student.id = :studentId")
    Integer findMaxTotalCreditsByStudentId(@Param("studentId") Long studentId);
    List<Result> findByStudentIdAndSemester(Long studentId, int semester);
    @Transactional
    @Modifying
    @Query("DELETE FROM Result r WHERE r.courseEnrollment.id = :enrollmentId")
    void deleteByEnrollmentId(@Param("enrollmentId") Long enrollmentId);
    Optional<Result> findByStudentIdAndCourseIdAndSemester(Long studentId, Long courseId, int semester);
    // Custom query to fetch results by student ID and semester
   
}