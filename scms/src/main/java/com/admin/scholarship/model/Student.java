package com.admin.scholarship.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students") 
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private int id;
    
    @Column(name="studid")
    private String studid;
    
    @Column(name="name")
    private String name;
    
    @Column(name="income")
    private Double income;
    
    @Column(name="email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "tenth_percentage")
    private Double tenthPercentage;  
    
    @Column(name = "twelfth_percentage")
    private Double twelfthPercentage;  
    
    @Column(name = "cgpa")
    private Double cgpa;
    
    @Column(name = "scholarshiptype")
    private String scholarshiptype;
    
    
    public String getScholarshiptype() {
		return scholarshiptype;
	}

	public void setScholarshiptype(String scholarshiptype) {
		this.scholarshiptype = scholarshiptype;
	}


	@Column(name = "amount")
    private double amount;
    
    public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	

	@Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudid() {
		return studid;
    }
    
    public void setStudid(String studid) {
		this.studid=studid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Double getTenthPercentage() {
        return tenthPercentage;
    }

    public void setTenthPercentage(Double tenthPercentage) {
        this.tenthPercentage = tenthPercentage;
    }

    public Double getTwelfthPercentage() {
        return twelfthPercentage;
    }

    public void setTwelfthPercentage(Double twelfthPercentage) {
        this.twelfthPercentage = twelfthPercentage;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
