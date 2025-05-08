package com.ums.MODEL;

import java.util.List;

public class CourseStudentView {
    private Course course;
    private List<Student> students;

    public CourseStudentView(Course course, List<Student> students) {
        this.course = course;
        this.students = students;
    }

    // Getters and setters
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
