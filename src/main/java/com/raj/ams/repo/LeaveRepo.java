package com.raj.ams.repo;

import java.util.List;
import com.raj.ams.model.LeaveRequest;

public interface LeaveRepo {

	void save(LeaveRequest req);
	LeaveRequest findRequestById(int id);
	List<LeaveRequest> findAll();
	void update(LeaveRequest leaveRequest);

}

