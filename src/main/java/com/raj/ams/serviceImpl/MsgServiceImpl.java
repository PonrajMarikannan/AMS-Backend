package com.raj.ams.serviceImpl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.raj.ams.model.Message;
import com.raj.ams.repo.MsgRepo;
import com.raj.ams.service.MsgService;

@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    MsgRepo repo;

	public void sendmsg(Message msg) {
		repo.save(msg);
	}
	
	public List<Message> FindMsgId(int id) {
		System.out.print(id);
		return repo.findMsgById(id);
	}	
	

}
