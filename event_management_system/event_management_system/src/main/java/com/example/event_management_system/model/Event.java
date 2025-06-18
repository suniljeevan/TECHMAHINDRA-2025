package com.example.event_management_system.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "event_id", nullable = false, length = 255)
    private String eventId;

    @Version
    private int version;
    
    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "event_time", nullable = false)
    private LocalTime eventTime;

    @Column(name = "event_place", nullable = false)
    private String eventPlace;

    @Column(name = "event_description", nullable = false, columnDefinition = "TEXT")
    private String eventDescription;

    @Column(name = "event_image", nullable = false, columnDefinition = "TEXT")
    private String eventImage;

    // Constructors, getters, setters

    public Event() {}

    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
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
    public String getEventPlace() {
        return eventPlace;
    }
    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }
    public String getEventDescription() {
        return eventDescription;
    }
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
    public String getEventImage() {
        return eventImage;
    }
    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }
}
