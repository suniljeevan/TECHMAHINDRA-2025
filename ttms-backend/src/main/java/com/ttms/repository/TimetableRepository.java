package com.ttms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttms.model.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Integer>{

	Timetable findBySemNameAndSectionName(String semName, String sectionName);
	
}
