package com.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.cms.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudentId(String studentId);

    List<Enrollment> findByCourseCourseId(String courseId);

    Enrollment findByStudentIdAndCourse_CourseId(String studentId, String courseId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Enrollment e WHERE e.student.id = :studentId AND e.course.courseId = :courseId")
    void unenrollCourse(@Param("studentId") String studentId, @Param("courseId") String courseId);

    boolean existsByStudent_IdAndCourse_CourseId(String studentId, String courseId);

    @Modifying
    @Transactional
	void deleteByCourse_CourseId(String courseId);
}
