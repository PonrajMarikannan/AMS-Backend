package com.raj.ams.repoImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.raj.ams.model.Staff;
import com.raj.ams.repo.StaffRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class StaffRepoImpli implements StaffRepo {

	@Autowired
	EntityManager eManager;
	
	public void save(Staff staff) {
		eManager.merge(staff);
	}

	@Override
	public List<Staff> findAll() {
		String hql = "from Staff";
		Query query = eManager.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public Staff findById(int id) {
		return eManager.find(Staff.class, id);
	}

}
	


