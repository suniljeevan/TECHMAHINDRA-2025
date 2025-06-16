package com.ttms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="SEM_DTLS")
public class Semester {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer sid;
	private String semName;
	private String semDescription;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSemName() {
		return semName;
	}
	public void setSemName(String semName) {
		this.semName = semName;
	}
	public String getSemDescription() {
		return semDescription;
	}
	public void setSemDescription(String semDescription) {
		this.semDescription = semDescription;
	}
	@Override
	public String toString() {
		return "Semester [sid=" + sid + ", semName=" + semName + ", semDescription=" + semDescription + "]";
	}
	public Semester(Integer sid, String semName, String semDescription) {
		super();
		this.sid = sid;
		this.semName = semName;
		this.semDescription = semDescription;
	}
	public Semester() {
		super();
	}
	
}
