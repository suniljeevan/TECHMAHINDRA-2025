package com.ums.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ums.model.ActivityLog;
import com.ums.model.Admin;
import com.ums.model.AlumniEvent;
import com.ums.model.EventRegistration;
import com.ums.repository.AlumniEventRepository;
import com.ums.repository.EventRegistrationRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private AlumniEventRepository eventRepo;
    
    @Autowired
    private EventRegistrationRepository eventRegistrationRepo;
    
    @Autowired
    private ActivityLogService activityLogService;
    
    @Autowired
    private HttpSession session;
    
    @Override
    public AlumniEvent insertEvent(AlumniEvent event) {
    	  AlumniEvent savedEvent = eventRepo.save(event);

    	    Admin admin = (Admin) session.getAttribute("loggedInAdmin");
    	    if (admin != null) {
    	        ActivityLog log = new ActivityLog();
    	        log.setAdminId(admin.getAdminId());
    	        log.setAction("Created a new event: " + savedEvent.getTitle());
    	        log.setEntityType("AlumniEvent");
    	        log.setEntityId(savedEvent.getEventId());
    	        log.setTimestamp(new Timestamp(System.currentTimeMillis()));
    	        activityLogService.insertLog(log);
    	    }

    	    return savedEvent;
    }

    @Override
    public AlumniEvent updateEvent(AlumniEvent event, String eventId) {
        Optional<AlumniEvent> optionalEvent = eventRepo.findById(eventId);
        if (optionalEvent.isPresent()) {
            AlumniEvent existingEvent = optionalEvent.get();
            existingEvent.setTitle(event.getTitle());
            existingEvent.setDescription(event.getDescription());
            existingEvent.setVenue(event.getVenue());
            existingEvent.setEventDate(event.getEventDate());
            existingEvent.setEventTime(event.getEventTime());

            AlumniEvent updatedEvent = eventRepo.save(existingEvent);

            //Log the update
            Admin admin = (Admin) session.getAttribute("loggedInAdmin");
            if (admin != null) {
                ActivityLog log = new ActivityLog();
                log.setAdminId(admin.getAdminId());
                log.setAction("Updated event: " + updatedEvent.getTitle());
                log.setEntityType("AlumniEvent");
                log.setEntityId(updatedEvent.getEventId());
                log.setTimestamp(new Timestamp(System.currentTimeMillis()));
                activityLogService.insertLog(log);
            }

            return updatedEvent;
        } else {
            throw new RuntimeException("Event not found with ID: " + eventId);
        }
    }
    
    

    @Override
    @Transactional
    public void deleteEvent(String eventId) {
    	// First delete all registrations linked to this event
        eventRegistrationRepo.deleteByEventId(eventId);
        
        // Get event title before deleting (optional but useful for logs)
        String eventTitle = eventRepo.findById(eventId)
                                     .map(AlumniEvent::getTitle)
                                     .orElse("Unknown Event");
        // Then delete the event
        eventRepo.deleteById(eventId);
        
     // Log the deletion
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin != null) {
            ActivityLog log = new ActivityLog();
            log.setAdminId(admin.getAdminId());
            log.setAction("Deleted event: " + eventTitle);
            log.setEntityType("AlumniEvent");
            log.setEntityId(eventId);
            log.setTimestamp(new Timestamp(System.currentTimeMillis()));
            activityLogService.insertLog(log);
        }
    }

    @Override
    public List<AlumniEvent> fetchAllEvents() {
        return eventRepo.findAll();
    }
    
    @Override
    public AlumniEvent fetchEventById(String eventId) {
        return eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));
    }
    

    @Override
    public List<AlumniEvent> getEventsRegisteredByAlumni(int alumniId) {
        return eventRegistrationRepo.findEventsByAlumniId(alumniId);
    }
 
    @Override
    public boolean registerAlumniForEvent(int alumniId, String eventId) {
        boolean alreadyRegistered = eventRegistrationRepo.existsByAlumniIdAndEventId(alumniId, eventId);
        if (!alreadyRegistered) {
            EventRegistration registration = new EventRegistration();
            registration.setAlumniId(alumniId);
            registration.setEventId(eventId);
            eventRegistrationRepo.save(registration);
            return true;
        }
        return false;
    }
    

}
