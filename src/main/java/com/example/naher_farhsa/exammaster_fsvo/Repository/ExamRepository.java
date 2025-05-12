package com.example.naher_farhsa.exammaster_fsvo.Repository;

import com.example.naher_farhsa.exammaster_fsvo.Entity.Exam;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query("SELECT DISTINCT e.courseId FROM Exam e")
    List<Course> findAllAssignedCourses();

    @Query("SELECT DISTINCT e.hallId FROM Exam e")
    List<Integer> findAllAssignedHalls();

    void deleteByCourseId(Course courseId);

    Exam findByCourseId(Course courseId);
}
