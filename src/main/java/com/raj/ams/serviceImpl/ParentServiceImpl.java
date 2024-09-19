package com.raj.ams.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.raj.ams.model.Parent;
import com.raj.ams.repo.ParentRepo;
import com.raj.ams.service.ParentService;

@Service
public class ParentServiceImpl implements ParentService {

    @Autowired
    ParentRepo repo;

	public void addParent(Parent parent) {
		repo.save(parent);
	}

	public Parent getById(int id) {
		return repo.findParent(id);
	}

	public List<Parent> getAll() {
		return repo.findAll();
	}	

}
