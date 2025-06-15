package org.cms.SERVICES;

import java.util.List;

import org.cms.MODEL.Course;
import org.cms.MODEL.Student;
import org.cms.REPOSITORY.CourseRepository;
import org.cms.REPOSITORY.StudentRepository;

public class CourseServiceImpl implements CourseService{
	//Explicit Wiring/ Inject
		private CourseRepository repository;
		
		public CourseServiceImpl() {
			super();
		}
		public CourseServiceImpl(CourseRepository repository) {
			this.repository = repository;
		}
		
	public int insertCourse(Course s) {
		return repository.insertCourse(s);
	}
	public int updateCourse(Course s, String id) {
		return repository.updateCourse(s, id);
	}
	public int deleteCourse(String id) {
		return repository.deleteCourse(id);
	}
	public List<Course> fetchAll(){
		return repository.fetchAllCourses();
	}
	public Course fetchOneCourse(String id) {
		return null;
	}
}
