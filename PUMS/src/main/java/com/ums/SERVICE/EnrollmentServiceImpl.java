package com.ums.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ums.MODEL.Enrollment;
import com.ums.MODEL.Student;
import com.ums.REPOSITORY.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	private final EnrollmentRepository repo;
	@Autowired
	private EnrollmentRepository enrollmentRepository;
    public EnrollmentServiceImpl(EnrollmentRepository repo) {
        this.repo = repo;
    }

    public List<Enrollment> getAllEnrollments() {
        return repo.findAll();
    }

    public Enrollment getEnrollmentById(long id) {
        return repo.findById(id).orElse(null);
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return repo.save(enrollment);
    }

    public void deleteEnrollment(long id) {
        repo.deleteById(id);
    }
    public List<Student> getEnrolledStudents(Long courseId){
    	return enrollmentRepository.findStudentsByCourseId(courseId);
    }

}
