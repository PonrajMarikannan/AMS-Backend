package com.raj.ams.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.raj.ams.model.Nutrient;
import com.raj.ams.repo.NutrientRepo;
import com.raj.ams.service.NutrientService;

@Service
public class NutrientsServiceImpl implements NutrientService {

    @Autowired
    NutrientRepo repo;
    
    public Nutrient getNutrientById(String def) {
		return repo.findNutrientById(def);
	}

}
