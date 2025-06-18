package com.ums.model;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int senderId;
    private String senderType; // "ALUMNI" or "ADMIN"

    private int recipientId;
    private String recipientType; // "ALUMNI" or "ADMIN"

    private String subject;

    @Lob
    private String content;

    private boolean isRead = false;

    @Column(nullable = false, updatable = false)
    private Timestamp sentAt;

    @PrePersist
    protected void onCreate() {
        this.sentAt = new Timestamp(System.currentTimeMillis());
    }
    
    
    
	public Message() {
		super();
	}



	public Message(Long id, int senderId, String senderType, int recipientId, String recipientType, String subject,
			String content, boolean isRead, Timestamp sentAt) {
		super();
		this.id = id;
		this.senderId = senderId;
		this.senderType = senderType;
		this.recipientId = recipientId;
		this.recipientType = recipientType;
		this.subject = subject;
		this.content = content;
		this.isRead = isRead;
		this.sentAt = sentAt;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public String getSenderType() {
		return senderType;
	}

	public void setSenderType(String senderType) {
		this.senderType = senderType;
	}

	public int getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	public String getRecipientType() {
		return recipientType;
	}

	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Timestamp getSentAt() {
		return sentAt;
	}

	public void setSentAt(Timestamp sentAt) {
		this.sentAt = sentAt;
	}

    
}

