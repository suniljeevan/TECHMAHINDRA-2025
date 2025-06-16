package com.ttms.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="TIMETABLE_DTLS")
public class Timetable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer timetableid;
	
	private String sectionName;
    private String semName;
    private String facultyFirstName;
    
    @ElementCollection
 	private List<String> timetableData;

	public Integer getTimetableid() {
		return timetableid;
	}

	public void setTimetableid(Integer timetableid) {
		this.timetableid = timetableid;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSemName() {
		return semName;
	}

	public void setSemName(String semName) {
		this.semName = semName;
	}

	public List<String> getTimetableData() {
		return timetableData;
	}

	public void setTimetableData(List<String> timetableData) {
		this.timetableData = timetableData;
	}

	public String getFacultyFirstName() {
		return facultyFirstName;
	}

	public void setFacultyFirstName(String facultyFirstName) {
		this.facultyFirstName = facultyFirstName;
	}

	@Override
	public String toString() {
		return "Timetable [timetableid=" + timetableid + ", sectionName=" + sectionName + ", semName=" + semName
				+ ", facultyFirstName=" + facultyFirstName + ", timetableData=" + timetableData + "]";
	}

	public Timetable(Integer timetableid, String sectionName, String semName, String facultyFirstName,
			List<String> timetableData) {
		super();
		this.timetableid = timetableid;
		this.sectionName = sectionName;
		this.semName = semName;
		this.facultyFirstName = facultyFirstName;
		this.timetableData = timetableData;
	}

	public Timetable() {
		super();
	}

	
}
