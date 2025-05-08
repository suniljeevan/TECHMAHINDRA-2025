package com.ums.MODEL;

import jakarta.persistence.*;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;
    
    // Getters and Setters and constructors

	public Enrollment(long id, Student student, Course course) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
	}

	public Enrollment() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	@Override
	public String toString() {
	    return "Enrollment{" +
	            "id=" + id +
	            ", studentId=" + (student != null ? student.getSid() : "null") +
	            ", courseId=" + (course != null ? course.getCid() : "null") +
	            '}';
	}
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Enrollment that = (Enrollment) o;
	    return id == that.id;
	}

	@Override
	public int hashCode() {
	    return Long.hashCode(id);
	}
    
    
}
