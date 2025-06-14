package com.ams.MODEL;


import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
   // private String username;
    private String password;
    private String role;
    
    public User() {}
    
    public User(Long id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() 
	{ 
		return email;
	}
    public void setEmail(String email) 
    { 
    	this.email = email;
    	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	@Override
    public int hashCode() {
        return Objects.hash(email, id, password, role);
    }
	
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return Objects.equals(id, other.id)
            && Objects.equals(email, other.email)
            && Objects.equals(password, other.password)
            && Objects.equals(role, other.role);
    }
	
	@Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", role=" + role + "]";
    }
}
