package com.ums.service;

import java.util.List;
import com.ums.model.AlumniEvent;
import com.ums.model.EventRegistration;

public interface EventService {
    AlumniEvent insertEvent(AlumniEvent event);
    AlumniEvent updateEvent(AlumniEvent event, String eventId);
    void deleteEvent(String eventId);
    List<AlumniEvent> fetchAllEvents();
    AlumniEvent fetchEventById(String eventId);
    
 // Registration logic
    boolean registerAlumniForEvent(int alumniId, String eventId);    
    
 // In EventService
    List<AlumniEvent> getEventsRegisteredByAlumni(int alumniId);

}
