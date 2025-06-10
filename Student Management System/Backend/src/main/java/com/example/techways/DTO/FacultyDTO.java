package com.example.techways.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacultyDTO {
    private Long facultyId;
    private String facultyName;
    private String facultyEmail;
    private String facultyPassword;
    private String facultyPhone;
    private String facultyGender;
    private String facultyRole;
    private String facultyDepartment;
    private String facultyDesignation;
}
