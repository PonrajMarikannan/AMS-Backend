package com.raj.ams.service;


import java.util.List;

import com.raj.ams.model.Children;
import com.raj.ams.model.Message;
import com.raj.ams.model.Parent;

public interface MsgService {
	
    void sendmsg(Message msg);
    public List<Message> FindMsgId(int id); 
        
    
     
}
