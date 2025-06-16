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

import com.ttms.model.Section;
import com.ttms.service.SectionService;

@RestController
@RequestMapping("/section")
@CrossOrigin(origins = "http://localhost:3000")
public class SectionRESTController {
	
	@Autowired
	private SectionService sectionservice;
	
	@PostMapping("/create-section")
	public ResponseEntity<String> createCourse(@RequestBody Section section) {
		System.out.println("Received Course: "+section);
		String status= sectionservice.upsert(section);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	@GetMapping("/{secid}")
    public ResponseEntity<Section> getAdminById(@PathVariable Integer secid) {
		Section section = sectionservice.getById(secid);
        return new ResponseEntity<>(section, HttpStatus.OK);
    }
	
	@GetMapping("/all-sections")
	public ResponseEntity<List<Section>> getAllSections() {
		List<Section> allSections= sectionservice.getAllSections();
        return new ResponseEntity<>(allSections, HttpStatus.OK);
    }
	
	@GetMapping
	public ResponseEntity<List<Section>> handleDefaultCourseGet() {
        return new ResponseEntity<>(sectionservice.getAllSections(),HttpStatus.OK);
	}
	
	@PutMapping("/update-section")
	public ResponseEntity<String> updateSection(@RequestBody Section section) {
		String status= sectionservice.upsert(section);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{secid}")
	public ResponseEntity<String> deleteSection(@PathVariable Integer secid) {
		String status= sectionservice.deleteById(secid);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
}
