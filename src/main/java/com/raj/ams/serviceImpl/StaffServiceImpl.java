package com.raj.ams.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.raj.ams.model.Staff;
import com.raj.ams.repo.StaffRepo;
import com.raj.ams.service.StaffService;


@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepo repo;
    
    public void addStaff(Staff staff) {
        repo.save(staff);
    }

	public List<Staff> getAllStaff() {
		return repo.findAll();
	}

	public Staff getStaffById(int id) {
		return repo.findById(id);
	}

	
	
    
}
