package com.ttms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ADMIN_DTLS")
public class AdminUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String userRole;
	private String adminEmail;
	private String adminPassword;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", userRole=" + userRole + ", adminEmail=" + adminEmail + ", adminPassword="
				+ adminPassword + "]";
	}
	public AdminUser(Integer id, String userRole, String adminEmail, String adminPassword) {
		super();
		this.id = id;
		this.userRole = userRole;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
	}
	public AdminUser() {
		super();
	}
}