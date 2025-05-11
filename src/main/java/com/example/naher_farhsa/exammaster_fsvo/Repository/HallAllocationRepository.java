package com.example.naher_farhsa.exammaster_fsvo.Repository;



import com.example.naher_farhsa.exammaster_fsvo.Entity.HallAllocation;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Course;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallAllocationRepository extends JpaRepository<HallAllocation, Long> {

    void deleteByExam_ExamId(Long examId);

    List<HallAllocation> findByExam_CourseId(Course courseId);

}
