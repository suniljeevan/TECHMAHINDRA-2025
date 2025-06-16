package com.cms.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class CustomUser {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	@Column(unique = true, nullable = false)
	private String email;
	private String phone;
	private String password;
	private String role;
	private Date createDate;
}
