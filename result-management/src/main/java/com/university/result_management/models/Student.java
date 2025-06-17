package com.university.result_management.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  
    private Long id;

    private String name;

    @Column(unique = true)
    private String rollNumber;

    @Column(unique = true)
    private String email;
    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private int year;
    private Integer semester; 
    
}
