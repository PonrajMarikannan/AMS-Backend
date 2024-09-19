package com.raj.ams.repoImpl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raj.ams.model.AttendanceChild;
import com.raj.ams.repo.ChildAttendRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class ChildAttendRepoImpl implements ChildAttendRepo {

	@Autowired
	EntityManager eManager;

	public void save(AttendanceChild child) {
		eManager.persist(child);
	}

	
	@Override
	public List<AttendanceChild> findAllAttend() {
		String hql = "from AttendanceChild";
		Query query = eManager.createQuery(hql);
		return query.getResultList();
	}

}
	


