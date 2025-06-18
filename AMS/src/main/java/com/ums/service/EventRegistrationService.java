package com.ums.service;

import java.util.List;
import com.ums.model.EventRegistration;

public interface EventRegistrationService {
    EventRegistration registerAlumniForEvent(int alumniId, String eventId);
    List<EventRegistration> fetchRegistrationsByAlumni(int alumniId);
    List<EventRegistration> fetchRegistrationsByEvent(String eventId);
}
