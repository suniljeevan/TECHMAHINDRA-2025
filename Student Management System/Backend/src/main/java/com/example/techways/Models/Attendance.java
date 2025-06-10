package com.example.techways.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "attendance")
@NoArgsConstructor
@AllArgsConstructor

public class Attendance {
    // name, rollNumber, branch, section, subject, date

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attendanceId;
    private String name;
    private String rollNumber;
    private String branch;
    private String section;
    private String subject;
    private String date;
    private String status; // Present or Absent
    private String facultyName;
    
}
