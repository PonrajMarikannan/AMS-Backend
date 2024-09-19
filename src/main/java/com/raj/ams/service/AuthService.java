package com.raj.ams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.raj.ams.dto.LoginResponse;
import com.raj.ams.model.Admin;
import com.raj.ams.model.Parent;
import com.raj.ams.model.Staff;
import com.raj.ams.repoImpl.AdminRepoImpl;

@Service
public class AuthService {
    
    @Autowired
    private AdminRepoImpl adminRepoImpl;

    public LoginResponse authenticate(String email, String password) 
    {
    	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
   
    	Admin admin = adminRepoImpl.findAdminEmail(email);
        Staff staff = adminRepoImpl.findTeacherEmail(email);
        Parent parent = adminRepoImpl.findParentEmail(email);

        if (admin != null && admin.getPassword().equals(password)) {
        	int Aid = admin.getAdminId();
            return new LoginResponse(Aid,"Success","Admin");
        }
        
        else if(staff != null) {
        	if(bcrypt.matches(password,staff.getPassword())) {
        		int Sid = staff.getStaffId();
        		return new LoginResponse(Sid,"Success","Teacher");        		
        	}else {
        		return new LoginResponse(9999,"Fail","Unknown");
        	}
        }

        else if(parent != null ) {
    		
        	if(bcrypt.matches(password,parent.getPassword())) {
        		int Pid = parent.getParentId();
        		return new LoginResponse(Pid,"Success","Parent");        		
        	} else {
        		return new LoginResponse(9999,"Fail","Unknown");
        	}
        	
        } else {
        	return new LoginResponse(9999,"Fail","Unknown");        	
        }
        
    }

	public Staff getStaffById(int id) {
		return adminRepoImpl.getById(id);
	}

	public Object updatePassword(Staff s) {
		return adminRepoImpl.ChangePassword(s);
	}

	public void updatePassword(Parent p) {
		adminRepoImpl.ChangePassword(p);
		
	}

	
    
}


