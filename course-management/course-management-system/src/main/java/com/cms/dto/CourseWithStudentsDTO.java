package com.cms.dto;

import lombok.Data;
import java.util.List;

@Data
public class CourseWithStudentsDTO {
    private String courseId;
    private String courseName;
    private String courseDescription;
    private int courseCredits;
    private String courseType;
    private List<StudentDTO> enrolledStudents;
}
