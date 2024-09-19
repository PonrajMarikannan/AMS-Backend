package com.raj.ams.service;


import java.util.List;

import com.raj.ams.model.Children;
import com.raj.ams.model.LeaveRequest;

public interface LeaveService {
	
    void addRequest(LeaveRequest req);
    public List<LeaveRequest> getAllRequest();
    public LeaveRequest getRequestById(int id);
    public void updateRequest(LeaveRequest req);
     
}
