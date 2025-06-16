package com.techm.rpm.MODEL;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "studentId",length = 15)
    private String studentId;

    @Column(name = "studentName",length = 100, nullable = false)
    private String studentName;

    @Column(name = "studentEmail",length = 100, nullable = false, unique = true)
    private String studentEmail;

    @Column(name = "studentMobile",length = 15, unique = true)
    private String studentMobile;

    @ManyToOne
    @JoinColumn(name = "projectId")  // References ResearchProject.projectId
    private Project project;

    // Constructors
    public Student() {}

    // Getters and Setters

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentMobile() {
        return studentMobile;
    }

    public void setStudentMobile(String studentMobile) {
        this.studentMobile = studentMobile;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

	@Override
	public int hashCode() {
		return Objects.hash(project, studentEmail, studentId, studentMobile, studentName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(project, other.project) && Objects.equals(studentEmail, other.studentEmail)
				&& Objects.equals(studentId, other.studentId) && Objects.equals(studentMobile, other.studentMobile)
				&& Objects.equals(studentName, other.studentName);
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentEmail=" + studentEmail
				+ ", studentMobile=" + studentMobile + ", Project=" + project + "]";
	}

	public Student(String studentId, String studentName, String studentEmail, String studentMobile,
			com.techm.rpm.MODEL.Project project) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentMobile = studentMobile;
		project = project;
	}
	

}
