package com.ttms.service;

import java.util.List;

import com.ttms.model.Timetable;

public interface TimetableService {

	public String upsert(Timetable timetable);
    
    public Timetable getTimetableById(Integer timetableid);
    
    public List<Timetable> getAllTimetables();
    
    public String deleteById(Integer timetableid);

	public Timetable getTimetableBySemNameAndSectionName(String semName, String sectionName);
	
}
