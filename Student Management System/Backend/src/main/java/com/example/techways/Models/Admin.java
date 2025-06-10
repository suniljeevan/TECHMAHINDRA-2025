package com.example.techways.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Admin")
public class Admin {

    @Id
    @Column(name = "admin_id")
    private Integer adminId;

    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;
}
