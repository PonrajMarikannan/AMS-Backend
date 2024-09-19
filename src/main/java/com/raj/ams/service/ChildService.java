package com.raj.ams.service;


import java.util.List;

import com.raj.ams.model.Children;

public interface ChildService {
	
    void addChildren(Children child);
    public List<Children> getAllChild();
    public Children getChildById(int childId);
	void updateChild(Children child);
	List<Children> getAllChildDef();
	List<Children> getChildByParentId(int id);
        
     
}
