package com.ums.SERVICE;

import java.util.List;

import com.ums.MODEL.Enrollment;
import com.ums.MODEL.Student;

public interface EnrollmentService {
	List<Enrollment> getAllEnrollments();
	Enrollment getEnrollmentById(long id);
	Enrollment saveEnrollment(Enrollment enrollment);
    void deleteEnrollment(long id);
}
