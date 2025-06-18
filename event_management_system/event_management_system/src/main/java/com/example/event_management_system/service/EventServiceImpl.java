package com.example.event_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.event_management_system.model.Event;
import com.example.event_management_system.repository.EventRepository;
import java.util.*;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event addEvent(Event event) {
        if (event.getEventId() == null || event.getEventId().isEmpty()) {
            event.setEventId(UUID.randomUUID().toString());
        }
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        Optional<Event> existingEventOpt = eventRepository.findById(event.getEventId());
        if (existingEventOpt.isPresent()) {
            Event existingEvent = existingEventOpt.get();
            existingEvent.setEventName(event.getEventName());
            existingEvent.setEventDate(event.getEventDate());
            existingEvent.setEventTime(event.getEventTime());
            existingEvent.setEventPlace(event.getEventPlace());
            existingEvent.setEventDescription(event.getEventDescription());
            existingEvent.setEventImage(event.getEventImage());
            System.out.println("Updating event with ID: " + event.getEventId());

            return eventRepository.save(existingEvent);
        } else {
            throw new RuntimeException("Event not found with id: " + event.getEventId());
        }
    }

    @Override
    public void deleteEvent(String eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> getEventById(String eventId) {
        return eventRepository.findById(eventId);
    }
}
