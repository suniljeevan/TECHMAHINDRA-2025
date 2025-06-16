package com.techm.rpm.MODEL;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "researchproject")
public class Project {

    @Override
	public String toString() {
		return "Project [projectId=" + projectId + ", title=" + title + ", description=" + description + ", status="
				+ status + ", startDate=" + startDate + ", endDate=" + endDate + ", faculty=" + faculty
				+ ", department=" + department + ", projectType=" + projectType + ", fundedAmount=" + fundedAmount
				+ ", fundingAgency=" + fundingAgency + "]";
	}

	
    @Override
	public int hashCode() {
		return Objects.hash(department, description, endDate, faculty, fundedAmount, fundingAgency, projectId,
				projectType, startDate, status, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		return Objects.equals(department, other.department) && Objects.equals(description, other.description)
				&& Objects.equals(endDate, other.endDate) && Objects.equals(faculty, other.faculty)
				&& Objects.equals(fundedAmount, other.fundedAmount)
				&& Objects.equals(fundingAgency, other.fundingAgency) && Objects.equals(projectId, other.projectId)
				&& Objects.equals(projectType, other.projectType) && Objects.equals(startDate, other.startDate)
				&& Objects.equals(status, other.status) && Objects.equals(title, other.title);
	}

	

    public Project(String projectId, String title, String description, String status, LocalDate startDate,
			LocalDate endDate, Faculty faculty, String department, String projectType, Double fundedAmount,
			String fundingAgency) {
		super();
		this.projectId = projectId;
		this.title = title;
		this.description = description;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.faculty = faculty;
		this.department = department;
		this.projectType = projectType;
		this.fundedAmount = fundedAmount;
		this.fundingAgency = fundingAgency;
	}
    @Id
    @Column(name = "projectId",length = 6)
    private String projectId;
    
    @Column(length = 200, nullable = false)
    private String title;

	@Lob
    private String description;

    @Column(name = "status",length = 20)
    private String status = "Proposed";  // No enum, stored as plain string

    @Column(name = "startDate",nullable = false)
    private LocalDate startDate;
    
    @Column(name = "endDate",nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "facultyId", nullable = false)
    private Faculty faculty;

    @Column(name = "department",length = 100)
    private String department;

    @Column(name = "projectType",length = 10, nullable = false)
    private String projectType = "Inhouse";  // 'Inhouse' or 'Funded'

    @Column(name = "fundedAmount",columnDefinition = "DECIMAL(12,2)")
    private Double fundedAmount = 0.0;

    @Column(name = "fundingAgency",length = 200)
    private String fundingAgency;

    // Constructors
    public Project() {}

    // Getters and Setters
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public Double getFundedAmount() {
        return fundedAmount;
    }

    public void setFundedAmount(Double fundedAmount) {
        this.fundedAmount = fundedAmount;
    }

    public String getFundingAgency() {
        return fundingAgency;
    }

    public void setFundingAgency(String fundingAgency) {
        this.fundingAgency = fundingAgency;
    }
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();



}
