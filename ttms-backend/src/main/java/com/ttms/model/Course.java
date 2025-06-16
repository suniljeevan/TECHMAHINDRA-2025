package com.ttms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="COURSE_DTLS")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cid;
	private String courseName;
	private String courseDescription;
	private String semName;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	public String getSemName() {
		return semName;
	}
	public void setSemName(String semName) {
		this.semName = semName;
	}
	@Override
	public String toString() {
		return "Course [cid=" + cid + ", courseName=" + courseName + ", courseDescription=" + courseDescription
				+ ", semName=" + semName + "]";
	}
	public Course(Integer cid, String courseName, String courseDescription, String semName) {
		super();
		this.cid = cid;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.semName = semName;
	}
	public Course() {
		super();
	}
	
}
