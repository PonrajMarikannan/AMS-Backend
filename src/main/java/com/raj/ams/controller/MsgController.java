package com.raj.ams.controller;

import java.io.IOException;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.raj.ams.model.Message;
import com.raj.ams.model.Parent;
import com.raj.ams.model.Staff;
import com.raj.ams.service.MsgService;
import com.raj.ams.service.ParentService;
import com.raj.ams.service.StaffService;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/msg")
public class MsgController {

    private MsgService mservice;
    private ParentService parentservice;
    private StaffService staffservice;
    
    public MsgController(MsgService mservice, ParentService parentservice, StaffService staffservice) {
			super();
			this.mservice = mservice;
			this.parentservice = parentservice;
			this.staffservice = staffservice;
	}



	@PostMapping("/sendmsg")
    public ResponseEntity<String> submitChild(
            @RequestParam("messageBody") String messageBody,
            @RequestParam("senderId") int senderId,
            @RequestParam("receiverId") int receiverId)
            throws IOException {
    	
        Message msg = new Message();
        msg.setMessageBody(messageBody);
       
        Parent parent = parentservice.getById(receiverId);
        Staff staff = staffservice.getStaffById(senderId);
        
        Timestamp currentTimestamp = new Timestamp(new Date().getTime());

        msg.setReadTimestamp(currentTimestamp);
        msg.setReceiver(parent);
        msg.setTeacher(staff);
        msg.setStatus("sent");
        mservice.sendmsg(msg);
        return ResponseEntity.ok("Success");
    }

   
    
    @GetMapping("/getMsg/{id}")
   	public List<Message> getMsgById(@PathVariable("id") int id) {
   		return mservice.FindMsgId(id);
   	}
    

}

