package com.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ums.model.AlumniEvent;
import com.ums.model.EventRegistration;

import java.util.List;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Integer> {
	@Query("SELECT e FROM AlumniEvent e JOIN EventRegistration r ON e.eventId = r.eventId WHERE r.alumniId = :alumniId")
	List<AlumniEvent> findEventsByAlumniId(@Param("alumniId") int alumniId);
   
	List<EventRegistration> findByEventId(String eventId);
    boolean existsByAlumniIdAndEventId(int alumniId, String eventId);
    
    List<EventRegistration> findByAlumniId(int alumniId);
    
    //to delete registration tied to the event 
    void deleteByEventId(String eventId);

}
