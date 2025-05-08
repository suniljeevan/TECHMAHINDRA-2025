package com.ums.MODEL;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    private String name;
    private String email;
    private String address;
    private String year;

    @OneToMany(mappedBy = "student")
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "student")
    private List<Evaluation> results;
    
    // Getters & Setters and constructors
	
	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}
	public void setAttendanceList(List<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}
	public List<Enrollment> getEnrollments() {
		return enrollments;
	}
	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	public List<Evaluation> getResults() {
		return results;
	}
	public void setResults(List<Evaluation> results) {
		this.results = results;
	}
	
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public void setSid(long sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Student(Long sid, String name, String email, String address, String year, List<Attendance> attendanceList,
			List<Enrollment> enrollments, List<Evaluation> results) {
		super();
		this.sid = sid;
		this.name = name;
		this.email = email;
		this.address = address;
		this.year = year;
		this.attendanceList = attendanceList;
		this.enrollments = enrollments;
		this.results = results;
	}
	public Student() {
		super();
	}
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Student other = (Student) obj;
	    return sid != null && sid.equals(other.sid);
	}

	@Override
	public int hashCode() {
	    return Objects.hashCode(sid); // Handles null safely
	}
	@Override
	public String toString() {
	    return "Student [sid=" + sid + ", name=" + name + "]";
	}
	

   
    
}