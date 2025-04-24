package com.ums.MODEL;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cid;

    private String cname;
    private int credit;
    private String ctype;
    
    // Getters and Setters and constructors
    
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
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
	public Course(String cid, String cname, int credit, String ctype) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.credit = credit;
		this.ctype = ctype;
	}
	public Course() {
		super();
	}

   
    
    
}
