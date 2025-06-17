package com.university.result_management.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int semester;
    
    
    @ManyToOne
    private Course course; // Relationship with Course
    @ManyToOne
    private CourseEnrollment courseEnrollment;
    

    private Double GPA;
    
    private double cgpa;
    
    private double marks;

    private String remarks;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
    private int totalCredits;

}
