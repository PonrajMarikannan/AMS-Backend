package com.raj.ams.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;


@Entity
@Table(name = "Messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "senderId", nullable = false)
    private Staff teacher;

    @ManyToOne
    @JoinColumn(name = "receiverId", nullable = false)
    private Parent receiver;

    private String messageBody;

    private String status; 

    private Timestamp readTimestamp;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Staff getTeacher() {
		return teacher;
	}

	public void setTeacher(Staff teacher) {
		this.teacher = teacher;
	}

	public Parent getReceiver() {
		return receiver;
	}

	public void setReceiver(Parent receiver) {
		this.receiver = receiver;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getReadTimestamp() {
		return readTimestamp;
	}

	public void setReadTimestamp(Timestamp readTimestamp) {
		this.readTimestamp = readTimestamp;
	}

	public Message(Long messageId, Staff teacher, Parent receiver, String messageBody, String status,
			Timestamp readTimestamp) {
		super();
		this.messageId = messageId;
		this.teacher = teacher;
		this.receiver = receiver;
		this.messageBody = messageBody;
		this.status = status;
		this.readTimestamp = readTimestamp;
	}

	public Message() {
		super();
	}
 
}
