package com.raj.ams.repo;

import java.util.List;

import com.raj.ams.model.Parent;

public interface ParentRepo {

	void save(Parent parent);
	Parent findParent(int parentId);
	List<Parent> findAll();

}

