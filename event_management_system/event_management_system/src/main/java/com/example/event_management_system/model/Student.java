package com.example.event_management_system.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "registration_id", length = 20, nullable = false)
    private String registrationId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "college_mail_id", length = 100, nullable = false, unique = true)
    private String collegeMailId;

    @Column(name = "phone", length = 10, nullable = false)
    private String phone;

    @Column(name = "program", length = 50, nullable = false)
    private String program;

    @Column(name = "school", length = 100, nullable = false)
    private String school;

    @Column(name = "department", length = 100, nullable = false)
    private String department;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "semester", nullable = false)
    private int semester;

    @Column(name = "event_name", length = 100, nullable = false)
    private String eventName;

    @Column(name = "registration_datetime", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registrationDatetime;

    @PrePersist
    protected void onCreate() {
        registrationDatetime = LocalDateTime.now();
    }

    // Getters and Setters

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollegeMailId() {
        return collegeMailId;
    }

    public void setCollegeMailId(String collegeMailId) {
        this.collegeMailId = collegeMailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getRegistrationDatetime() {
        return registrationDatetime;
    }

    public void setRegistrationDatetime(LocalDateTime registrationDatetime) {
        this.registrationDatetime = registrationDatetime;
    }
}

