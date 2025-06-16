package com.techm.rpm.MODEL;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facultyId;

    @Column(name = "facultyName",length = 50, nullable = false)
    private String facultyName;

    @Column(name = "facultyEmail",length = 50, nullable = false, unique = true)
    private String facultyEmail;

    @Column(name = "facultyPassword",length = 50, nullable = false)
    private String facultyPassword;

    @Column(name = "facultyMobile",length = 20, nullable = false, unique = true)
    private String facultyMobile;

    @Column(name = "facultyDepartment",length = 50)
    private String facultyDepartment;

	public Integer getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getFacultyEmail() {
		return facultyEmail;
	}

	public void setFacultyEmail(String facultyEmail) {
		this.facultyEmail = facultyEmail;
	}

	public String getFacultyPassword() {
		return facultyPassword;
	}

	public void setFacultyPassword(String facultyPassword) {
		this.facultyPassword = facultyPassword;
	}

	public String getFacultyMobile() {
		return facultyMobile;
	}

	public void setFacultyMobile(String facultyMobile) {
		this.facultyMobile = facultyMobile;
	}

	public String getFacultyDepartment() {
		return facultyDepartment;
	}

	public void setFacultyDepartment(String facultyDepartment) {
		this.facultyDepartment = facultyDepartment;
	}

	public Faculty(Integer facultyId, String facultyName, String facultyEmail, String facultyPassword,
			String facultyMobile, String facultyDepartment) {
		super();
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		this.facultyEmail = facultyEmail;
		this.facultyPassword = facultyPassword;
		this.facultyMobile = facultyMobile;
		this.facultyDepartment = facultyDepartment;
	}

	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", facultyName=" + facultyName + ", facultyEmail=" + facultyEmail
				+ ", facultyPassword=" + facultyPassword + ", facultyMobile=" + facultyMobile + ", facultyDepartment="
				+ facultyDepartment + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(facultyDepartment, facultyEmail, facultyId, facultyMobile, facultyName, facultyPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		return Objects.equals(facultyDepartment, other.facultyDepartment)
				&& Objects.equals(facultyEmail, other.facultyEmail) && Objects.equals(facultyId, other.facultyId)
				&& Objects.equals(facultyMobile, other.facultyMobile) && Objects.equals(facultyName, other.facultyName)
				&& Objects.equals(facultyPassword, other.facultyPassword);
	}

	public Faculty() {
		super();
	}

    // Constructors, Getters, Setters
}
