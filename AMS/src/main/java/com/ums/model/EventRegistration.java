package com.ums.model;

import jakarta.persistence.*;

@Entity
public class EventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int registrationId;

    @Column(nullable = false)
    private int alumniId;

    @Column(nullable = false)
    private String eventId; // VARCHAR

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp registrationDate;

    // Constructors
    public EventRegistration() {}

    public EventRegistration(int registrationId, int alumniId, String eventId) {
        this.registrationId = registrationId;
        this.alumniId = alumniId;
        this.eventId = eventId;
    }

    // Getters and Setters
    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public int getAlumniId() {
        return alumniId;
    }

    public void setAlumniId(int alumniId) {
        this.alumniId = alumniId;
    }

    public String getEventId() {
        return eventId;
    }
 
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public java.sql.Timestamp getRegistrationDate() {
        return registrationDate;
    }
 
    public void setRegistrationDate(java.sql.Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }
}
