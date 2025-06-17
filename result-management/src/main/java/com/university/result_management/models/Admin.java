package com.university.result_management.models;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Table(name = "admin") // optional, but clarifies table name
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;
}
