package com.ums.model;

import jakarta.persistence.*;

@Entity
public class Alumni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int alumniId;

    private String name;
    private String username;
    private String email;
    private String password;
    private int graduationYear;
    private String universityId;

    @Column(nullable = false)
    private boolean passwordChangeRequired = true;

    // ✅ New fields
    private String companyName;
    private String jobTitle;
    private String imageUrl;
    private String branch;
    private String role;

    // --- Constructors ---
    public Alumni() {}

    public Alumni(int alumniId, String name, String username, String email, String password, int graduationYear,
                  String universityId, String companyName, String jobTitle, String imageUrl, String branch, String role) {
        this.alumniId = alumniId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.graduationYear = graduationYear;
        this.universityId = universityId;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.imageUrl = imageUrl;
        this.branch = branch;
        this.role = role;
    }

    // --- Getters and Setters ---

    public int getAlumniId() { return alumniId; }
    public void setAlumniId(int alumniId) { this.alumniId = alumniId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getGraduationYear() { return graduationYear; }
    public void setGraduationYear(int graduationYear) { this.graduationYear = graduationYear; }

    public String getUniversityId() { return universityId; }
    public void setUniversityId(String universityId) { this.universityId = universityId; }

    public boolean isPasswordChangeRequired() { return passwordChangeRequired; }
    public void setPasswordChangeRequired(boolean passwordChangeRequired) { this.passwordChangeRequired = passwordChangeRequired; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    
}
