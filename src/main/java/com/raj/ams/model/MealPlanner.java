package com.raj.ams.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "MealPlanner")
public class MealPlanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealPlannerId;

    @Column(nullable = false)
    private String deficiency;

    @Column(nullable = false)
    private String preferFood;

    @ManyToOne
    @JoinColumn(name = "childId", nullable = false)
    private Children child;

	public Long getMealPlannerId() {
		return mealPlannerId;
	}

	public void setMealPlannerId(Long mealPlannerId) {
		this.mealPlannerId = mealPlannerId;
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

	public Children getChild() {
		return child;
	}

	public void setChild(Children child) {
		this.child = child;
	}

	public MealPlanner() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

   
}



