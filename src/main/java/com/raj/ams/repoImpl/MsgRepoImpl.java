package com.raj.ams.repoImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.raj.ams.model.Message;
import com.raj.ams.repo.MsgRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class MsgRepoImpl implements MsgRepo {

	@Autowired
	EntityManager eManager;

	public void save(Message msg) {
		eManager.persist(msg);
	}

	
	@Override
	public List<Message> findMsgById(int pid) {
	    String hql = "from Message m where m.receiver.parentId = :id";
	    Query query = eManager.createQuery(hql);
	    query.setParameter("id", pid);
	    return query.getResultList();
	}
	
}
	


