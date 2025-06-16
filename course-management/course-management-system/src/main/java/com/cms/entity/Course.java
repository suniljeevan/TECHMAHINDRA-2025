package com.cms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "course")
public class Course {
	@Id
	@Column(unique = true)
	private String courseId;
	private String courseName;
	private String courseDescription;
	private int courseCredits;
	private String courseType;
}
