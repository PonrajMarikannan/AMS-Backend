package com.raj.ams.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.raj.ams.model.Nutrient;
import com.raj.ams.service.NutrientService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/nutrients")
public class NutrientsController {

    private NutrientService service;
    
    public NutrientsController(NutrientService service) {
		super();
		this.service = service;
	}

	@GetMapping("/getnutrient/{deficiency}")
	public Nutrient getNutrientDetailsByDef(@PathVariable("deficiency") String deficiency) {
		return service.getNutrientById(deficiency);
	}


}

