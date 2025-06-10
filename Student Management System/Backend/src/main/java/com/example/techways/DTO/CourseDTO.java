package com.example.techways.DTO;

import lombok.Data;

@Data
public class CourseDTO {
    private String name;
    private String code;
    private String description;
    private String duration;
    private Integer credits;
}