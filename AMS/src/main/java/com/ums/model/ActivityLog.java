package com.ums.model;

import jakarta.persistence.*;

@Entity
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logId;

    @Column(nullable = false)
    private int adminId;

    @Column(nullable = false)
    private String action;

    private String entityType;
    private String entityId;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp timestamp;

    // Constructors
    public ActivityLog() {}

    public ActivityLog(int logId, int adminId, String action, String entityType, String entityId) {
        this.logId = logId;
        this.adminId = adminId;
        this.action = action;
        this.entityType = entityType;
        this.entityId = entityId;
    }

    // Getters and Setters
    public int getLogId() {
        return logId;
    }
  
    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getAdminId() {
        return adminId;
    }
  
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAction() {
        return action;
    }
 
    public void setAction(String action) {
        this.action = action;
    }

    public String getEntityType() {
        return entityType;
    }
  
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityId() {
        return entityId;
    }
  
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public java.sql.Timestamp getTimestamp() {
        return timestamp;
    }
 
    public void setTimestamp(java.sql.Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
