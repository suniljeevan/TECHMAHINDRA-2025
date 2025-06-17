package com.admin.scholarship.model;


import jakarta.persistence.*;

@Entity
@Table(name = "scholarships")
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="amount")
    private Double amount;
    @Column(name="egc1")
    private String egc1;
    @Column(name="egc2")
    private String egc2;
    @Column(name="egc3")
    private String egc3;
    @Column(name="egc4")
    private String egc4;
    @Column(name="egc5")
    private String egc5;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getEgc1() {
		return egc1;
	}
	public void setEgc1(String egc1) {
		this.egc1 = egc1;
	}
	public String getEgc2() {
		return egc2;
	}
	public void setEgc2(String egc2) {
		this.egc2 = egc2;
	}
	public String getEgc3() {
		return egc3;
	}
	public void setEgc3(String egc3) {
		this.egc3 = egc3;
	}
	public String getEgc4() {
		return egc4;
	}
	public void setEgc4(String egc4) {
		this.egc4 = egc4;
	}
	public String getEgc5() {
		return egc5;
	}
	public void setEgc5(String egc5) {
		this.egc5 = egc5;
	}

}
