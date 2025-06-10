package com.example.techways.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.techways.Models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    // we cant use table name in query, we have to use entity name
    // use alias instead of *
    @Query(value = "SELECT c FROM Course c")
    public List<Course> getAllCoursesRepository();


    // fetch using course name and duration
    @Query(value = "SELECT c FROM Course c WHERE courseName = :val and courseDuration = :courseDurationVal")
    public Course fetchUsingName(@Param("val") String courseName, @Param("courseDurationVal") String courseDuration);

    // fetch using course credit
    @Query(value = "SELECT c FROM Course c WHERE courseCredits = :courseCreditsVal")
    public Course fetchUsingCredit(@Param("courseCreditsVal") int courseCredits);

}
