package com.cms.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
	@NotEmpty
	private String id;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String email;
	@NotEmpty
	private String phone;
	@NotEmpty
	@Size(min = 8, message = "Minimum password length is 8 characters")
	private String password;
}
