package com.raj.ams.service;



import java.util.List;

import com.raj.ams.model.AttendanceChild;

public interface ChildAttendService {
	
    void addAttend(AttendanceChild attend);
	List<AttendanceChild> getAllAttend();
    
     
}
