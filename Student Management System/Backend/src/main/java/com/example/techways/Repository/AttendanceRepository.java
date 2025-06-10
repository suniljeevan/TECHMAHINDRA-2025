package com.example.techways.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.techways.Models.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    @Query(value = "SELECT a FROM Attendance a WHERE branch = :branch and section = :section and subject = :subject")
    public List<Attendance> fetchUsingBranchSectionAndSubject(String branch, String section, String subject);

}
