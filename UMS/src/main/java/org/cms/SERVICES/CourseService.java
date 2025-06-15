package org.cms.SERVICES;
import java.util.List;
import org.cms.MODEL.Course;

public interface CourseService {
	public int insertCourse(Course s);
	public int updateCourse(Course s, String id);
	public int deleteCourse(String id);
	public List<Course> fetchAll();
	public Course fetchOneCourse(String id);
}
