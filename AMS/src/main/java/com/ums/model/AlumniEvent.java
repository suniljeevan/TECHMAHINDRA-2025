package com.ums.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class AlumniEvent {
    @Id
    private String eventId; // VARCHAR based

    @Column(nullable = false)
    private String title;

    @Lob
    private String description;

    private String venue;
    private LocalDate eventDate;
    private LocalTime eventTime;

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = new Timestamp(System.currentTimeMillis());
        }
    }

    // Constructors
    public AlumniEvent() {}

    public AlumniEvent(String eventId, String title, String description, String venue, LocalDate eventDate, LocalTime eventTime) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.venue = venue;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    // Getters and Setters
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
  
    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue() {
        return venue;
    }
  
    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }
 
    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }
 
    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }
 
    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
