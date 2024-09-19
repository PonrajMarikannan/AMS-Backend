package com.raj.ams.repo;

import java.util.List;
import com.raj.ams.model.Staff;

public interface StaffRepo {

	void save(Staff staff);
	List<Staff> findAll();
	Staff findById(int id);
    
}

