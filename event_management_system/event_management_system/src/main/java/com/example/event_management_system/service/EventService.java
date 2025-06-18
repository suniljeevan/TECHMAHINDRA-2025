package com.example.event_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.example.event_management_system.model.Event;

public interface EventService {

    Event addEvent(Event event);
    @Transactional
    Event updateEvent(Event event);

    void deleteEvent(String eventId);

    List<Event> getAllEvents();

    Optional<Event> getEventById(String eventId);
}
