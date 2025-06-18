package com.ums.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ums.model.EventRegistration;
import com.ums.repository.EventRegistrationRepository;

@Service
public class EventRegistrationServiceImpl implements EventRegistrationService {

    @Autowired
    private EventRegistrationRepository registrationRepo;

    @Override
    public EventRegistration registerAlumniForEvent(int alumniId, String eventId) {
        EventRegistration registration = new EventRegistration();
        registration.setAlumniId(alumniId);
        registration.setEventId(eventId);
        registration.setRegistrationDate(new Timestamp(System.currentTimeMillis()));
        return registrationRepo.save(registration);
    }

    @Override
    public List<EventRegistration> fetchRegistrationsByAlumni(int alumniId) {
        return registrationRepo.findByAlumniId(alumniId);
    }

    @Override
    public List<EventRegistration> fetchRegistrationsByEvent(String eventId) {
        return registrationRepo.findByEventId(eventId);
    }
}
