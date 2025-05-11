package com.example.naher_farhsa.exammaster_fsvo.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Course {
    CS101("CS101", "Data Structures"), //
    CS102("CS102", "Operating Systems"),//
    CS103("CS103", "Database Management"),
    CS104("CS104", "Computer Networks"), //
    CS105("CS105", "Computer Organization"),
    CS106("CS106", "Cloud Computing"),
    CS107("CS107", "Cyber Security"),
    CS108("CS108", "Internet of Things");
    private final String courseCode;
    private final String courseName;
}
