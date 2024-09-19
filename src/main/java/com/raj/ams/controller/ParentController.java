package com.raj.ams.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.raj.ams.model.Parent;
import com.raj.ams.service.AuthService;
import com.raj.ams.service.ParentService;
import com.raj.ams.serviceImpl.CustomMail;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/parent")
public class ParentController {

    private ParentService service;
    private AuthService authservice;
    private CustomMail mailservice;
    
    public ParentController(ParentService service, AuthService authservice, CustomMail mailservice) {
		super();
		this.service = service;
		this.authservice = authservice;
		this.mailservice = mailservice;
	}

	@PostMapping
    public ResponseEntity<Integer> submitApplication(
            @RequestParam("name") String name,
            @RequestParam("phnum") String phnum,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("aadharNum") String aadharNum,
            @RequestParam("address") String address,
            @RequestParam("photo") MultipartFile photo) throws IOException {
    	
        byte[] photoBytes = null;
    	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        if (photo != null && !photo.isEmpty()) {
        	photoBytes = photo.getBytes();
        }

        Parent parent = new Parent();
    	String encryptPassword = bcrypt.encode(password);

        parent.setName(name);
        parent.setPhnum(phnum);
        parent.setEmail(email);
        parent.setPassword(encryptPassword);
        parent.setAddress(address);
        parent.setAadharNum(aadharNum);
        parent.setPhoto(photoBytes);
        
        service.addParent(parent);
        return ResponseEntity.ok(parent.getParentId());
    }
    
    @PutMapping("/updatePassParent")
    public String updatePassword(
            @RequestParam("parentId") int  parentId,
            @RequestParam("password") String password,
            @RequestParam("newPassword") String newPassword) {
        String msg;
    	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    	System.out.print(parentId);
        Parent p =  service.getById(parentId);
        String old = p.getPassword();
        
        try {
	        if(bcrypt.matches(password, old)){
	        	String encryptPassword = bcrypt.encode(newPassword);
	        	p.setPassword(encryptPassword);
	        	authservice.updatePassword(p);
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

    
    @GetMapping("/getParent/{id}")
   	public Parent getParentDetailsById(@PathVariable("id") int id) {
   		return service.getById(id);
   	}
    
    @GetMapping("/all")
    public List<Parent> viewAllParent() {
        return service.getAll();
    }
    
    @PostMapping("/admission")
    public ResponseEntity<String> sendCustomEmail(@RequestBody EmailRequest emailRequest) {
        try {
        	String content = String.format(
        		    "Dear %s,\n\n" +
        		    "Your Child Admission has been successfully Done in our Anganwadi Centre.\n\n" +
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

            String subject = "Child Admission Successfully Completed";
            mailservice.sendEmail(emailRequest.getEmail(), subject, content);
            return ResponseEntity.ok("MailSend");
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
        }
    }
    
    @PostMapping("/health")
    public ResponseEntity<String> sendHealthEmail(@RequestBody HealthDetails hd) {
    
        try {
        	String content = String.format(
        		    "Dear parent\n\n" +
        		    "Your Child Health details.\n\n" +
        		    "Your Child Only eat this type of foods for this deficiency.\n\n" +
        		    "Child Name : %s\n"+
        		    "Deficiency : %s\n" +
        		    "Preferfoods: %s\n" +
        		   
        		    "Best regards,\n" +
        		    "The Anganwadi Team",
        		    hd.getName(),
        		    hd.getDeficiency(),
        		    hd.getPreferedFood()
        		);

            String subject = "Child Health Details";
            mailservice.sendEmail(hd.getEmail(), subject, content);
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
    
    public static class HealthDetails {
    	 private String name;
    	 private String email;
         private String deficiency;
         private String preferedFood;
         
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getDeficiency() {
			return deficiency;
		}
		public void setDeficiency(String deficiency) {
			this.deficiency = deficiency;
		}
		
		public String getPreferedFood() {
			return preferedFood;
		}
		public void setPreferedFood(String preferedFood) {
			this.preferedFood = preferedFood;
		}
		public HealthDetails() {
			super();
		}
		public HealthDetails(String name, String email, String deficiency, String preferedFood) {
			super();
			this.name = name;
			this.email = email;
			this.deficiency = deficiency;
			this.preferedFood = preferedFood;
		}
		
		
         
         
    }

    
    
    
   
//    
//
//    @PutMapping("/updateStatus")
//    public ResponseEntity<String> updateStatus(
//            @RequestParam("id") Integer id,
//            @RequestParam("status") String status) {
//        
//        try {
//            ExporterApplication.Status newStatus = ExporterApplication.Status.valueOf(status);
//            ExporterApplication application = serviceimpl.getApp(id);
//            if (application != null) {
//                application.setStatus(newStatus);
//                serviceimpl.updateApp(application);
//                return ResponseEntity.ok("Status updated successfully.");
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Application not found.");
//            }
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid status value.");
//        }
//    }
//    
   
    
}

