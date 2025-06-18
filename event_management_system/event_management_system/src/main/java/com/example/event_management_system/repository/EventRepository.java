package com.example.event_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.event_management_system.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    // Spring Data JPA provides CRUD methods out of the box
}
