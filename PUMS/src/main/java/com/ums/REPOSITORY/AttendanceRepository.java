package com.ums.REPOSITORY;

import com.ums.MODEL.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
@Query("SELECT DISTINCT a.course FROM Attendance a WHERE a.date = :today")
List<Course> findCoursesWithAttendanceToday(@Param("today") String today);
void deleteByCourseCidAndDate(Long courseId, String date);
List<Attendance> findByCourseCidAndDate(Long courseId, String date);
}