package com.university.result_management.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data

@NoArgsConstructor
@AllArgsConstructor
public class CourseEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
   

    private Double caMarks = 0.0;       // Continuous Assessment
    private Double midtermMarks = 0.0;  // Midterm Exam
    private Double endtermMarks = 0.0;  // Final/Endterm Exam
    
    private Double totalMarksObtained=0.0;

    private String grade;

    private String status;
    
    private int attemptNumber = 1; // Default attempt


    
}
