package com.raj.ams.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.raj.ams.model.AttendanceChild;
import com.raj.ams.model.Children;
import com.raj.ams.model.Staff;
import com.raj.ams.service.ChildService;
import com.raj.ams.service.ChildAttendService;
import com.raj.ams.service.StaffService;
import org.springframework.web.bind.annotation.PostMapping;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/childattend")
public class ChildAttendController {

    
    private ChildAttendService childattendservice;
    private ChildService childservice;
    private StaffService staffservice;
    
    
    
    public ChildAttendController(ChildAttendService childattendservice, ChildService childservice,
			StaffService staffservice) {
		super();
		this.childattendservice = childattendservice;
		this.childservice = childservice;
		this.staffservice = staffservice;
	}

	@PostMapping("/attend")
    public ResponseEntity<String> attendChild(
            @RequestParam("status") String status,
            @RequestParam("staffId") int staffId,
            @RequestParam("childId") int childId
            )           
             throws IOException {

        AttendanceChild atd = new AttendanceChild();
        
        Staff staff = staffservice.getStaffById(staffId);
        Children children = childservice.getChildById(childId);
        
        atd.setStatus(status);
        atd.setChild(children);
        atd.setTeacher(staff);
        atd.setDate(new java.sql.Date(System.currentTimeMillis()));
        childattendservice.addAttend(atd);
        return ResponseEntity.ok("Success");
    }
    
    @GetMapping("/all")
    public List<AttendanceChild> viewAllAttendance() {
        return childattendservice.getAllAttend();
    }


}

