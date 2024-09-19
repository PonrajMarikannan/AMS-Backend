package com.raj.ams.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.raj.ams.model.Staff;
import com.raj.ams.service.StaffService;
import com.raj.ams.serviceImpl.CustomMail;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/staff")
public class StaffController {

    private StaffService service;
    private CustomMail mailservice;
    
    
    public StaffController(StaffService service, CustomMail mailservice) {
		super();
		this.service = service;
		this.mailservice = mailservice;
	}


	@PostMapping("/addstaff")
    public String addStaff(@RequestBody Staff staff) {
    	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    	
        try {
        	String encryptPassword = bcrypt.encode(staff.getPassword());
        	staff.setPassword(encryptPassword);
            service.addStaff(staff);
            return "Success";
        } catch (Exception e) {
            return "Failure";
        }
    }
    
    
    @GetMapping("/all")
    public List<Staff> viewAllStaff() {
        return service.getAllStaff();
    }
    
    @GetMapping("/getStaff/{id}")
	public Staff getStaffDetailsById(@PathVariable("id") int id) {
		return service.getStaffById(id);
	}

    
    @PostMapping("/mail")
    public ResponseEntity<String> sendCustomEmail(@RequestBody EmailRequest emailRequest) {
        try {
        	String content = String.format(
        		    "Dear %s,\n\n" +
        		    "Your account has been successfully created with our Anganwadi Centre.\n\n" +
        		    "Here are your login credentials:\n" +
        		    "Email: %s\n" +
        		    "Password: %s\n\n" +
        		    "For security reasons, we recommend changing your password after logging in for the first time. To do this, please follow these steps:\n" +
        		    "1. Log in to your account using the credentials provided.\n" +
        		    "2. Go to the Change Password in Sidebar.\n" +
        		    "3. Change your password.\n" +
        		    "4. Follow the instructions to set a new password.\n\n" +
        		    "If you have any questions or need assistance, feel free to contact our support team.\n\n" +
        		    "Best regards,\n" +
        		    "The Anganwadi Team",
        		    emailRequest.getEmail().split("@")[0],
        		    emailRequest.getEmail(),
        		    emailRequest.getPassword()
        		);

            String subject = "Successfully Account Creation";
            mailservice.sendEmail(emailRequest.getEmail(), subject, content);
            return ResponseEntity.ok("MailSend");
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
        }
    }
    
    
    
    public static class EmailRequest {
        private String email;
        private String password;
        
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public EmailRequest(String email, String password) {
			super();
			this.email = email;
			this.password = password;
		}
		public EmailRequest() {
			super();
		}    
    }
}
