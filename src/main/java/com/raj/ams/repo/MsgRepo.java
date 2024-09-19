package com.raj.ams.repo;

import java.util.List;
import com.raj.ams.model.Message;

public interface MsgRepo {

	void save(Message msg);
	List<Message> findMsgById(int id);
}

