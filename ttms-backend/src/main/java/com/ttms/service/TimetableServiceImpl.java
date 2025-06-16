package com.ttms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttms.model.Timetable;
import com.ttms.repository.TimetableRepository;

@Service
public class TimetableServiceImpl implements TimetableService{
	
	@Autowired
	private TimetableRepository timetableRepo;
	
	public String upsert(Timetable timetable) {
		timetableRepo.save(timetable);
		return "Success";
	}
	
	public Timetable getTimetableBySemNameAndSectionName(String semName, String sectionName) {
	    return timetableRepo.findBySemNameAndSectionName(semName, sectionName);
	}

	
	public Timetable getTimetableById(Integer timetableid) {
		Optional<Timetable> findById= timetableRepo.findById(timetableid);
		
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
	
	public List<Timetable> getAllTimetables() {
		return timetableRepo.findAll();
	}
	
	public String deleteById(Integer timetableid) {
		if(timetableRepo.existsById(timetableid)) {
			timetableRepo.deleteById(timetableid);
			return "Deletion success";
		} else {
			return "No Data found";
		}
	}
}