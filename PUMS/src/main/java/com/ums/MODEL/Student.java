package com.ums.MODEL;
import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long sid;

     String name;
     String email;
     String address;
     String year;
    
    // Getters & Setters and constructors
    
	public Student(long sid, String name, String email, String address, String year) {
		super();
		this.sid = sid;
		this.name = name;
		this.email = email;
		this.address = address;
		this.year = year;
	}
	public Student() {
		super();
	}
	public long getSid() {
		return sid;
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

   
    
}