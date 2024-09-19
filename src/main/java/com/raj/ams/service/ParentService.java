package com.raj.ams.service;


import java.util.List;

import com.raj.ams.model.Parent;

public interface ParentService {
	
    void addParent(Parent parent);
    public List<Parent> getAll();
	Parent getById(int parentId);
     
}
