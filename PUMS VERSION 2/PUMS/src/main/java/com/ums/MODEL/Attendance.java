package com.ums.MODEL;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    private String date;
    private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Attendance(Long id, Student student, Course course, String date, String status) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
		this.date = date;
		this.status = status;
	}
	public Attendance() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(course, date, id, status, student);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attendance other = (Attendance) obj;
		return Objects.equals(course, other.course) && Objects.equals(date, other.date) && Objects.equals(id, other.id)
				&& Objects.equals(status, other.status) && Objects.equals(student, other.student);
	}
	@Override
	public String toString() {
		return "Attendance [id=" + id + ", student=" + student + ", course=" + course + ", date=" + date + ", status="
				+ status + "]";
	}
    
}