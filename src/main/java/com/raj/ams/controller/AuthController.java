package com.raj.ams.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.raj.ams.dto.LoginResponse;
import com.raj.ams.model.Staff;
import com.raj.ams.service.AuthService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {


    private AuthService authservice;
    
    public AuthController(AuthService authservice) {
		super();
		this.authservice = authservice;
	}

	@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
       try {
    	   
    	   	LoginResponse response =  authservice.authenticate(email,password);
    	   	System.out.print(response.getRole());
    	   		if(response!=null) {
    	   			return ResponseEntity.ok(response);
    	   		} else {
    	   			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials. Please try again.");    	   			
    	   		}
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error. Please try again later.");
        }
    }
    
    @PutMapping("/updatePass")
    public String updatePassword(
            @RequestParam("staffId") int  staffId,
            @RequestParam("password") String password,
            @RequestParam("newPassword") String newPassword) {
        String msg;
    	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    	System.out.print(staffId);
        Staff s =  authservice.getStaffById(staffId);
        String old = s.getPassword();
        
        try {
	        if(bcrypt.matches(password, old)){
	        	String encryptPassword = bcrypt.encode(newPassword);
	        	s.setPassword(encryptPassword);
	        	authservice.updatePassword(s);
				msg="Success";
	        }
	        else {
	        	msg="Password Does not Match!";
	        }
        }
		catch(Exception e) {
			msg="Failure";
		}
		return msg;
	}
    
    @GetMapping("/getStaff/{id}")
   	public Staff getStaffDetailsById(@PathVariable("id") int id) {
   		return authservice.getStaffById(id);
   	}
    
    

}
    
