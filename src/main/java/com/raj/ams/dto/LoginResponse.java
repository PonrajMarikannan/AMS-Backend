package com.raj.ams.dto;

public class LoginResponse {
	
	private int id;
	private String status;
    private String role;
    
	public LoginResponse(int id, String status, String role) {
		super();
		this.id = id;
		this.status = status;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
}
