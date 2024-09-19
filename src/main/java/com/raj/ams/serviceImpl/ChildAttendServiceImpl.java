package com.raj.ams.serviceImpl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.raj.ams.model.AttendanceChild;
import com.raj.ams.repo.ChildAttendRepo;
import com.raj.ams.service.ChildAttendService;


@Service
public class ChildAttendServiceImpl implements ChildAttendService {

    @Autowired
    ChildAttendRepo repo;

    public void addAttend(AttendanceChild data) {
		repo.save(data);		
	}

	@Override
	public List<AttendanceChild> getAllAttend() {
		return repo.findAllAttend();
	}

}
