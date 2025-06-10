package com.example.techways.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techways.Models.Attendance;
import com.example.techways.Repository.AttendanceRepository;

@Service
public class AttendanceService {
    
    @Autowired
    public AttendanceRepository attendanceRepository;

    public List<Attendance> markAttendance(String branch, String section, String subject) {
        return attendanceRepository.fetchUsingBranchSectionAndSubject(branch, section, subject);
    }
    
}
