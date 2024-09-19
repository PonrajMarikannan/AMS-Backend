package com.raj.ams.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Staff")
public class Staff {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffId;

    private String username;

    private String password;

    @Column(unique = true, nullable = false)
    private String email;
    
    private String role;

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Staff(int staffId, String username, String password, String email, String role) {
		super();
		this.staffId = staffId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    
    
}
