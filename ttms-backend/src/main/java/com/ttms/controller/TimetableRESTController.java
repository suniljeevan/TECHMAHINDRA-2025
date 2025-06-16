package com.ttms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttms.model.Timetable;
import com.ttms.service.TimetableService;

@RestController
@RequestMapping("/timetable")
@CrossOrigin(origins = "http://localhost:3000")
public class TimetableRESTController {
	
	@Autowired
	private TimetableService timetableService;
	
	@PostMapping("/create-timetable")
	public ResponseEntity<String> createTimetable(@RequestBody Timetable timetable) {
		System.out.println("Received Timetable: "+timetable);
		String status= timetableService.upsert(timetable);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	@GetMapping("/{timetableid}")
    public ResponseEntity<Timetable> getAdminById(@PathVariable Integer timetableid) {
		Timetable timetable = timetableService.getTimetableById(timetableid);
        return new ResponseEntity<>(timetable, HttpStatus.OK);
    }
	
	@GetMapping("/get-timetable/{semName}/{sectionName}")
	public ResponseEntity<Timetable> getTimetableBySemAndSection(@PathVariable String semName, @PathVariable String sectionName) {
	    Timetable timetable = timetableService.getTimetableBySemNameAndSectionName(semName, sectionName);
	    if (timetable != null) {
	        return new ResponseEntity<>(timetable, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/all-timetables")
	public ResponseEntity<List<Timetable>> getAllTimetables() {
		List<Timetable> alltimetables= timetableService.getAllTimetables();
        return new ResponseEntity<>(alltimetables, HttpStatus.OK);
    }
	
	@GetMapping
	public ResponseEntity<List<Timetable>> handleDefaultTimetableGet() {
        return new ResponseEntity<>(timetableService.getAllTimetables(),HttpStatus.OK);
	}
	
	@PutMapping("/update-timetable")
	public ResponseEntity<String> updateTimetable(@RequestBody Timetable timetable) {
		String status= timetableService.upsert(timetable);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{timetableid}")
	public ResponseEntity<String> deletetimetable(@PathVariable Integer timetableid) {
		String status= timetableService.deleteById(timetableid);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
}
