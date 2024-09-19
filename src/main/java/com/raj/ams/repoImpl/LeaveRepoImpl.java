package com.raj.ams.repoImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.raj.ams.model.LeaveRequest;
import com.raj.ams.repo.LeaveRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class LeaveRepoImpl implements LeaveRepo {

	@Autowired
	EntityManager eManager;

	public void save(LeaveRequest req) {
		eManager.persist(req);
	}

	@Override
	public List<LeaveRequest> findAll() {
		String hql = "from LeaveRequest";
		Query query = eManager.createQuery(hql);
		return query.getResultList();
	}
	public LeaveRequest findRequestById(int id) {
		return eManager.find(LeaveRequest.class, id);	
	}

	@Override
	public void update(LeaveRequest req) {
		eManager.merge(req);
	}
}
	


