package com.example.techways.DTO;

import java.util.List;

import com.example.techways.Models.NewUsers;
import com.example.techways.Models.Course;
import com.example.techways.Models.Faculty;
import com.example.techways.Models.Student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class RequestResponse {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String email;
    private String password;
    private String role;

    private NewUsers users;
    private List<NewUsers> usersList;

    private Faculty faculty;
    private List<Faculty> facultyList;

    private Student student;
    private List<Student> studentList;

    private Course course;
    private List<Course> courseList;


}
