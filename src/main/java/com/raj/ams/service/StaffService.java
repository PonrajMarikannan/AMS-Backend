package com.raj.ams.service;

import java.util.List;
import com.raj.ams.model.Staff;

public interface StaffService {
	
    void addStaff(Staff staff);
    public List<Staff> getAllStaff();
    public Staff getStaffById(int staffId);
   
}
