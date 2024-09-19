package com.raj.ams.repoImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.raj.ams.model.Children;
import com.raj.ams.repo.ChildRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ChildRepoImpl implements ChildRepo {

	@Autowired
	EntityManager eManager;

	public void save(Children child) {
		eManager.persist(child);
	}

	@Override
	public List<Children> findAll() {
		String hql = "from Children";
		Query query = eManager.createQuery(hql);
		return query.getResultList();
	}
	public Children findChildById(int id) {
		return eManager.find(Children.class, id);	
	}

	@Override
	public void update(Children child) {
		eManager.merge(child);
	}
	
	@Override
	public List<Children> findAllDef() {
	    String hql = "from Children c where c.deficiency <> 'none'";
	    Query query = eManager.createQuery(hql);
	    return query.getResultList();
	}

	@Override
	public List<Children> findChildByParentId(int id) {
	    String hql = "from Children c where c.parent.parentId = :parentId";
	    Query query = eManager.createQuery(hql);
	    query.setParameter("parentId", id);
	    return query.getResultList();
	}

}
	


