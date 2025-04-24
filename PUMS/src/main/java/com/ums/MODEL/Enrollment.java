package com.ums.MODEL;

import jakarta.persistence.*;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;
    
    // Getters and Setters and constructors

	public Enrollment(String id, Student student, Course course) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
	}

	public Enrollment() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

   
    
    
}
