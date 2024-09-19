package com.raj.ams.repoImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.raj.ams.model.Parent;
import com.raj.ams.repo.ParentRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ParentRepoImpl implements ParentRepo {

	@Autowired
	EntityManager eManager;

	public void save(Parent parent) {
		eManager.persist(parent);
	}

	@Override
	public Parent findParent(int parentId) {
		return eManager.find(Parent.class, parentId);	
	}

	@Override
	public List<Parent> findAll() {
		String hql = "from Parent";
		Query query = eManager.createQuery(hql);
		return query.getResultList();
	}
	
}
	


