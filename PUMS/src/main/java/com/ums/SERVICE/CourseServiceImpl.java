package com.ums.SERVICE;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ums.MODEL.Course;
import com.ums.REPOSITORY.AttendanceRepository;
import com.ums.REPOSITORY.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	
	private final CourseRepository repo;
	@Autowired
	private AttendanceRepository attendanceRepository;
    public CourseServiceImpl(CourseRepository repo) {
        this.repo = repo;
    }

    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    public Course getCourseById(long id) {
        return repo.findById(id).orElse(null);
    }

    public Course saveCourse(Course course) {
        return repo.save(course);
    }

    public void deleteCourse(long id) {
        repo.deleteById(id);
    }
    public List<Course> getCoursesForToday(){
    	 String today = LocalDate.now().toString(); // yyyy-MM-dd
    	    return attendanceRepository.findCoursesWithAttendanceToday(today);
    }
}
