package com.ums.MODEL;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cid;

    private String cname;
    private int credit;
    private String ctype;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "course")
    private List<Exam> exams;
    private boolean attendanceSubmitted;
	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

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

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	
	public Course(long cid, String cname, int credit, String ctype, Faculty faculty, List<Attendance> attendanceList,
			List<Enrollment> enrollments, List<Exam> exams, boolean attendanceSubmitted) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.credit = credit;
		this.ctype = ctype;
		this.faculty = faculty;
		this.attendanceList = attendanceList;
		this.enrollments = enrollments;
		this.exams = exams;
		this.attendanceSubmitted = attendanceSubmitted;
	}

	public boolean isAttendanceSubmitted() {
		return attendanceSubmitted;
	}

	public void setAttendanceSubmitted(boolean attendanceSubmitted) {
		this.attendanceSubmitted = attendanceSubmitted;
	}

	public Course() {
		super();
	}

	

	@Override
	public String toString() {
	    return "Course{" +
	            "cid=" + cid +
	            ", cname='" + cname + '\'' +
	            ", credit=" + credit +
	            ", ctype='" + ctype + '\'' +
	            ", attendanceSubmitted='" + attendanceSubmitted + '\'' +
	            ", faculty=" + (faculty != null ? faculty.getName() : "null") +
	            '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Course course = (Course) o;
		return cid == course.cid &&
		       Objects.equals(cname, course.cname);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cid, cname);
	}
   
    
}