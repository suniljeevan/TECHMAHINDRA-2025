package com.ttms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="STUDENT_DTLS")
public class StudentUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer studentid;
	private String studentFirstName;
	private String studentLastName;
	private String studentEmail;
	private String studentPassword;
	private String studentContactNo;
	private String studentAddress;
	private String userRole;
	private String studentSemester;
	private String studentSection;
	public Integer getStudentid() {
		return studentid;
	}
	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}
	public String getStudentFirstName() {
		return studentFirstName;
	}
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}
	public String getStudentLastName() {
		return studentLastName;
	}
	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	public String getStudentContactNo() {
		return studentContactNo;
	}
	public void setStudentContactNo(String studentContactNo) {
		this.studentContactNo = studentContactNo;
	}
	public String getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getStudentSemester() {
		return studentSemester;
	}
	public void setStudentSemester(String studentSemester) {
		this.studentSemester = studentSemester;
	}
	public String getStudentSection() {
		return studentSection;
	}
	public void setStudentSection(String studentSection) {
		this.studentSection = studentSection;
	}
	@Override
	public String toString() {
		return "StudentUser [studentid=" + studentid + ", studentFirstName=" + studentFirstName + ", studentLastName="
				+ studentLastName + ", studentEmail=" + studentEmail + ", studentPassword=" + studentPassword
				+ ", studentContactNo=" + studentContactNo + ", studentAddress=" + studentAddress + ", userRole="
				+ userRole + ", studentSemester=" + studentSemester + ", studentSection=" + studentSection + "]";
	}
	public StudentUser(Integer studentid, String studentFirstName, String studentLastName, String studentEmail,
			String studentPassword, String studentContactNo, String studentAddress, String userRole,
			String studentSemester, String studentSection) {
		super();
		this.studentid = studentid;
		this.studentFirstName = studentFirstName;
		this.studentLastName = studentLastName;
		this.studentEmail = studentEmail;
		this.studentPassword = studentPassword;
		this.studentContactNo = studentContactNo;
		this.studentAddress = studentAddress;
		this.userRole = userRole;
		this.studentSemester = studentSemester;
		this.studentSection = studentSection;
	}
	public StudentUser() {
		super();
	}
	
	
}