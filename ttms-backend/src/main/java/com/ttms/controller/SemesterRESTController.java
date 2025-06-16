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

import com.ttms.model.Semester;
import com.ttms.service.SemesterService;

@RestController
@RequestMapping("/semester")
@CrossOrigin(origins = "http://localhost:3000")
public class SemesterRESTController {
	
	@Autowired
	private SemesterService semesterService;
	
	@PostMapping("/create-semester")
	public ResponseEntity<String> createSemester(@RequestBody Semester semester) {
		System.out.println("Received Semester: "+semester);
		String status= semesterService.upsert(semester);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	@GetMapping("/{sid}")
    public ResponseEntity<Semester> getAdminById(@PathVariable Integer sid) {
		Semester semester = semesterService.getById(sid);
        return new ResponseEntity<>(semester, HttpStatus.OK);
    }
	
	@GetMapping("/all-semesters")
	public ResponseEntity<List<Semester>> getAllSemesters() {
		List<Semester> allSemesters= semesterService.getAllSemesters();
        return new ResponseEntity<>(allSemesters, HttpStatus.OK);
    }
	
	@GetMapping
	public ResponseEntity<List<Semester>> handleDefaultSemesterGet() {
        return new ResponseEntity<>(semesterService.getAllSemesters(),HttpStatus.OK);
	}
	
	@PutMapping("/update-semester")
	public ResponseEntity<String> updateSemester(@RequestBody Semester semester) {
		String status= semesterService.upsert(semester);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{sid}")
	public ResponseEntity<String> deleteSemester(@PathVariable Integer sid) {
		String status= semesterService.deleteById(sid);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
}
