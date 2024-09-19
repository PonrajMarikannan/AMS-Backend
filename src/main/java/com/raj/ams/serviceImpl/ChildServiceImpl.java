package com.raj.ams.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.raj.ams.model.Children;
import com.raj.ams.repo.ChildRepo;
import com.raj.ams.service.ChildService;

@Service
public class ChildServiceImpl implements ChildService {

    @Autowired
    ChildRepo repo;

	public void addChildren(Children child) {
		repo.save(child);
	}

	public List<Children> getAllChild() {
		return repo.findAll();
	}

	public Children getChildById(int id) {
		return repo.findChildById(id);
	}

	public void updateChild(Children child) {
		repo.update(child);	
	}
	
	@Override
	public List<Children> getAllChildDef() {
		return  repo.findAllDef();
	}

	@Override
	public List<Children> getChildByParentId(int id) {
		return repo.findChildByParentId(id);
	}

//	public List<Children> getAllChildDef() {
//		return findAllDef();
//	}

//	private List<Children> findAllDef() {
//		return repo.findAllDef();
//	}

	

		
	

}
