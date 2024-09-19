package com.raj.ams.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "Parent")
public class Parent {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int parentId;

    private String name;
    
    @Column(unique = true)
    private String phnum;

    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Lob
    @Column(name = "photo",length = 100000)
    private byte[] photo;
    
    @Column(unique = true)
    private String aadharNum;
    
    public Parent(int parentId, String name, String phnum, String email, String password, byte[] photo,
			String aadharNum, String address) {
		super();
		this.parentId = parentId;
		this.name = name;
		this.phnum = phnum;
		this.email = email;
		this.password = password;
		this.photo = photo;
		this.aadharNum = aadharNum;
		this.address = address;
	}
	private String address;
    
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhnum() {
		return phnum;
	}
	public void setPhnum(String phnum) {
		this.phnum = phnum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getAadharNum() {
		return aadharNum;
	}
	public void setAadharNum(String aadharNum) {
		this.aadharNum = aadharNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Parent() {
		super();
	}
    
    
    
}
