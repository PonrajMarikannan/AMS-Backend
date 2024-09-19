package com.raj.ams.serviceImpl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.raj.ams.model.LeaveRequest;
import com.raj.ams.repo.LeaveRepo;
import com.raj.ams.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    LeaveRepo repo;

    public void addRequest(LeaveRequest req) {
		repo.save(req);
	}

	public List<LeaveRequest> getAllRequest() {
		return repo.findAll();
	}

	public LeaveRequest getRequestById(int id) {
		return repo.findRequestById(id);
	}

	public void updateRequest(LeaveRequest leaveRequest) {
		repo.update(leaveRequest);
		
	}
}
