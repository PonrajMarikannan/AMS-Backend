package com.raj.ams.repo;

import java.util.List;

import com.raj.ams.model.AttendanceChild;

public interface ChildAttendRepo {

	void save(AttendanceChild data);
	List<AttendanceChild> findAllAttend();
	
}

