package com.raj.ams.repo;

import java.util.List;

import com.raj.ams.model.Children;

public interface ChildRepo {

	void save(Children child);
	List<Children> findAll();
	Children findChildById(int id);
	void update(Children child);
	List<Children> findAllDef();
	List<Children> findChildByParentId(int id);
}

