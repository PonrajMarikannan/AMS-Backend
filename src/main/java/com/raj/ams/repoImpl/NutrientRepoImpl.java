package com.raj.ams.repoImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.raj.ams.model.Nutrient;
import com.raj.ams.repo.NutrientRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class NutrientRepoImpl implements NutrientRepo {

	@Autowired
	EntityManager eManager;
	
	public Nutrient findNutrientById(String deficiency) {
        String jpql = "SELECT n FROM Nutrient n WHERE n.deficiency = :deficiency";
        TypedQuery<Nutrient> query = eManager.createQuery(jpql, Nutrient.class);
        query.setParameter("deficiency", deficiency);
        return query.getSingleResult();
    }
	
}
	


