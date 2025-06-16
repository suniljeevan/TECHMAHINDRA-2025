package com.ttms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="FACULTY_DTLS")
public class FacultyUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer facultyid;
	private String userRole;
	private String facultyEmail;
	private String facultyPassword;
	private String facultyFirstName;
	private String facultyLastName;
	private String facultyContact;
	private String facultyAddress;
	public Integer getFacultyid() {
		return facultyid;
	}
	public void setFacultyid(Integer facultyid) {
		this.facultyid = facultyid;
	}
	public String getuserRole() {
		return userRole;
	}
	public void setuserRole(String userRole) {
		this.userRole = userRole;
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
	public String getFacultyFirstName() {
		return facultyFirstName;
	}
	public void setFacultyFirstName(String facultyFirstName) {
		this.facultyFirstName = facultyFirstName;
	}
	public String getFacultyLastName() {
		return facultyLastName;
	}
	public void setFacultyLastName(String facultyLastName) {
		this.facultyLastName = facultyLastName;
	}
	public String getFacultyContact() {
		return facultyContact;
	}
	public void setFacultyContact(String facultyContact) {
		this.facultyContact = facultyContact;
	}
	public String getFacultyAddress() {
		return facultyAddress;
	}
	public void setFacultyAddress(String facultyAddress) {
		this.facultyAddress = facultyAddress;
	}
	@Override
	public String toString() {
		return "FacultyUser [facultyid=" + facultyid + ", userRole=" + userRole + ", facultyEmail=" + facultyEmail
				+ ", facultyPassword=" + facultyPassword + ", facultyFirstName=" + facultyFirstName
				+ ", facultyLastName=" + facultyLastName + ", facultyContact=" + facultyContact + ", facultyAddress="
				+ facultyAddress + "]";
	}
	public FacultyUser(Integer facultyid, String userRole, String facultyEmail, String facultyPassword,
			String facultyFirstName, String facultyLastName, String facultyContact, String facultyAddress) {
		super();
		this.facultyid = facultyid;
		this.userRole = userRole;
		this.facultyEmail = facultyEmail;
		this.facultyPassword = facultyPassword;
		this.facultyFirstName = facultyFirstName;
		this.facultyLastName = facultyLastName;
		this.facultyContact = facultyContact;
		this.facultyAddress = facultyAddress;
	}
	public FacultyUser() {
		super();
	}
}
