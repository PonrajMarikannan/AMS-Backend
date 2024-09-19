package com.raj.ams.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.raj.ams.model.LeaveRequest;
import com.raj.ams.model.Staff;
import com.raj.ams.service.LeaveService;
import com.raj.ams.service.StaffService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/leave")
public class LeaveController {

    private LeaveService leaveservice;
    private StaffService staffService;
    
    public LeaveController(LeaveService leaveservice, StaffService staffService) {
		super();
		this.leaveservice = leaveservice;
		this.staffService = staffService;
	}


	@PostMapping("/addrequest")
    public String addStaff(@RequestParam("leaveType") String leaveType,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("reason") String reason,
            @RequestParam("staffId") int staffId) throws IOException{    	
        try {
        	LeaveRequest req = new LeaveRequest();
        	
            req.setLeaveType(leaveType);
            req.setStartDate(java.sql.Date.valueOf(startDate));
        	req.setEndDate(java.sql.Date.valueOf(endDate));
        	req.setReason(reason);
        	
        	Staff staff = staffService.getStaffById(staffId);
            req.setStaff(staff);
            leaveservice.addRequest(req);
            return "Success";
        } catch (Exception e) {
            return "Failure";
        }
    }

    
    @GetMapping("/all")
    public List<LeaveRequest> viewAllRequest() {
        return leaveservice.getAllRequest();
    }

  @GetMapping("/getRequest/{id}")
	public LeaveRequest getRequestDetailsById(@PathVariable("id") int id) {
		return leaveservice.getRequestById(id);
	}
  @PutMapping("/updaterequest/{id}")
  public ResponseEntity<String> updateLeaveStatus(
          @PathVariable("id") int id,
          @RequestBody String status) {
      try {
          LeaveRequest leaveRequest = leaveservice.getRequestById(id);
          
          if (leaveRequest == null) {
              return new ResponseEntity<>("Leave request not found", HttpStatus.NOT_FOUND);
          }
          
          leaveRequest.setStatus(status);
          leaveservice.updateRequest(leaveRequest);
          
          return new ResponseEntity<>("Success", HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>("Failure", HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

}

