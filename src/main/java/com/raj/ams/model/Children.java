package com.raj.ams.model;

import java.sql.Date;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Children")
public class Children {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childId;

    private String name;
    private java.sql.Date dateOfBirth;
    private String gender;

    @Lob
    @Column(name = "bcertificate",length = 100000)
    private byte[] birthCertificate;
    
    @Lob
    @Column(name = "photo",length = 100000)
    private byte[] photo;

    private Double weight;
    private Double height;

   
    private String nutritionalStatus;
    
    private String deficiency;

    private java.sql.Date healthUpdateDate;
    
    @ManyToOne(targetEntity = Parent.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "parentId")
    private Parent parent;

    public enum NutritionalStatus {
        HEALTHY, NOT_HEALTHY
    }

    public Long getChildId() {
		return childId;
	}

	
	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(java.sql.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte[] getBirthCertificate() {
		return birthCertificate;
	}

	public void setBirthCertificate(byte[] birthCertificate) {
		this.birthCertificate = birthCertificate;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getDeficiency() {
		return deficiency;
	}

	public void setDeficiency(String deficiency) {
		this.deficiency = deficiency;
	}

	public java.sql.Date getHealthUpdateDate() {
		return healthUpdateDate;
	}

	public void setHealthUpdateDate(java.sql.Date healthUpdateDate) {
		this.healthUpdateDate = healthUpdateDate;
	}

	public Parent getParent() {
		return parent;
	}

	public Children() {
		super();
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}


	public String getNutritionalStatus() {
		return nutritionalStatus;
	}


	public void setNutritionalStatus(String nutritionalStatus) {
		this.nutritionalStatus = nutritionalStatus;
	}


	public Children(Long childId, String name, Date dateOfBirth, String gender, byte[] birthCertificate, byte[] photo,
			Double weight, Double height, String nutritionalStatus, String deficiency, Date healthUpdateDate,
			Parent parent) {
		super();
		this.childId = childId;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.birthCertificate = birthCertificate;
		this.photo = photo;
		this.weight = weight;
		this.height = height;
		this.nutritionalStatus = nutritionalStatus;
		this.deficiency = deficiency;
		this.healthUpdateDate = healthUpdateDate;
		this.parent = parent;
	}


}

