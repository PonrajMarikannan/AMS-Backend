package com.raj.ams.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.raj.ams.controller.MsgController;
import com.raj.ams.model.Message;
import com.raj.ams.model.Parent;
import com.raj.ams.model.Staff;
import com.raj.ams.service.MsgService;
import com.raj.ams.service.ParentService;
import com.raj.ams.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class MsgServiceImplTest {

    @InjectMocks
    private MsgController msgController;

    @Mock
    private MsgService msgService;

    @Mock
    private ParentService parentService;

    @Mock
    private StaffService staffService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("deprecation")
	@Test
    void testSubmitChild_Success() throws IOException {
        
        String messageBody = "Hello!";
        int senderId = 1;
        int receiverId = 2;
        
        Parent receiver = new Parent();
        Staff sender = new Staff();
        
        when(parentService.getById(receiverId)).thenReturn(receiver);
        when(staffService.getStaffById(senderId)).thenReturn(sender);
        
        
        ResponseEntity<String> response = msgController.submitChild(messageBody, senderId, receiverId);
        
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Success", response.getBody());
        verify(msgService, times(1)).sendmsg(any(Message.class));
    }

    @Test
    void testGetMsgById() {
        
        int id = 1;
        Message message = new Message();
        List<Message> messages = Collections.singletonList(message);
        when(msgService.FindMsgId(id)).thenReturn(messages);
        
        List<Message> result = msgController.getMsgById(id);

        assertEquals(messages, result);
    }

    
}

