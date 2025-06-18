package com.ums.SERVICE;


import java.util.List;

import com.ums.MODEL.Course;

public interface CourseService {
	List<Course> getAllCourses();
    Course getCourseById(long id);
    Course saveCourse(Course course);
    void deleteCourse(long id);
}
