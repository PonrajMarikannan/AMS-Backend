package com.raj.ams.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Nutrients")
public class Nutrient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nutrientsId;

    private String deficiency;

    private String preferFood;

	public Long getNutrientsId() {
		return nutrientsId;
	}

	public void setNutrientsId(Long nutrientsId) {
		this.nutrientsId = nutrientsId;
	}

	public String getDeficiency() {
		return deficiency;
	}

	public void setDeficiency(String deficiency) {
		this.deficiency = deficiency;
	}

	public String getPreferFood() {
		return preferFood;
	}

	public void setPreferFood(String preferFood) {
		this.preferFood = preferFood;
	}

	public Nutrient(Long nutrientsId, String deficiency, String preferFood) {
		super();
		this.nutrientsId = nutrientsId;
		this.deficiency = deficiency;
		this.preferFood = preferFood;
	}

	public Nutrient() {
		super();
	}

    
}


