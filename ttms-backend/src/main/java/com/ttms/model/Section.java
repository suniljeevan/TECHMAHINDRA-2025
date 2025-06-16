package com.ttms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="SECTION_DTLS")
public class Section {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer secid;
	private String sectionName;
	private String sectionDescription;
	private String semName;
	private String facultyFirstName;
	public Integer getSecid() {
		return secid;
	}
	public void setSecid(Integer secid) {
		this.secid = secid;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getSectionDescription() {
		return sectionDescription;
	}
	public void setSectionDescription(String sectionDescription) {
		this.sectionDescription = sectionDescription;
	}
	public String getSemName() {
		return semName;
	}
	public void setSemName(String semName) {
		this.semName = semName;
	}
	public String getFacultyFirstName() {
		return facultyFirstName;
	}
	public void setFacultyFirstName(String facultyFirstName) {
		this.facultyFirstName = facultyFirstName;
	}
	@Override
	public String toString() {
		return "Section [secid=" + secid + ", sectionName=" + sectionName + ", sectionDescription=" + sectionDescription
				+ ", semName=" + semName + ", facultyFirstName=" + facultyFirstName + "]";
	}
	public Section(Integer secid, String sectionName, String sectionDescription, String semName,
			String facultyFirstName) {
		super();
		this.secid = secid;
		this.sectionName = sectionName;
		this.sectionDescription = sectionDescription;
		this.semName = semName;
		this.facultyFirstName = facultyFirstName;
	}
	public Section() {
		super();
	}
}
