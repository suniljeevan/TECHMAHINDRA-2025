package com.example.techways.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techways.Models.Attendance;
import com.example.techways.Service.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/markAttendance/{branch}/{section}/{subject}")
    public List<Attendance> markAttendanceController(@PathVariable String branch, @PathVariable String section, @PathVariable String subject) {
        return attendanceService.markAttendance(branch, section, subject);
    }
    
}
