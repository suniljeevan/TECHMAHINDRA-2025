package com.presidency.PUMS;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ums.MODEL.Attendance;
import com.ums.REPOSITORY.AttendanceRepository;
import com.ums.REPOSITORY.CourseRepository;

import jakarta.annotation.PostConstruct;

@ComponentScan("com.ums")
@EnableJpaRepositories(basePackages = "com.ums.REPOSITORY")
@EntityScan(basePackages = "com.ums.MODEL")
@SpringBootApplication(scanBasePackages = "com.ums")
public class PumsApplication {
	  @Autowired
	    private CourseRepository courseRepository;

	    @Autowired
	    private AttendanceRepository attendanceRepository;

	    public static void main(String[] args) {
	        SpringApplication.run(PumsApplication.class, args);
	    }

	    @PostConstruct
	    public void init() {
	        Attendance attendance = new Attendance();
	        attendance.setCourse(courseRepository.findById(1L).orElseThrow(() -> new RuntimeException("Course not found")));
	        attendance.setDate(LocalDate.now().toString()); // or use LocalDate type directly
	        attendance.setSubmissionStatus("not submitted");
	        attendanceRepository.save(attendance);
	        attendance.setCourse(courseRepository.findById(3L).orElseThrow(() -> new RuntimeException("Course not found")));
	        attendance.setDate(LocalDate.now().toString()); // or use LocalDate type directly
	        attendance.setSubmissionStatus("not submitted");
	        attendanceRepository.save(attendance);
	        attendance.setCourse(courseRepository.findById(4L).orElseThrow(() -> new RuntimeException("Course not found")));
	        attendance.setDate(LocalDate.now().toString()); // or use LocalDate type directly
	        attendance.setSubmissionStatus("not submitted");
	        attendanceRepository.save(attendance);
	        attendance.setCourse(courseRepository.findById(5L).orElseThrow(() -> new RuntimeException("Course not found")));
	        attendance.setDate(LocalDate.now().toString()); // or use LocalDate type directly
	        attendance.setSubmissionStatus("not submitted");
	        attendanceRepository.save(attendance);
	        
	    }
	}