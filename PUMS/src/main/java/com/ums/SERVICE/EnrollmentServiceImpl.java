package com.ums.SERVICE;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ums.MODEL.Enrollment;
import com.ums.REPOSITORY.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	private final EnrollmentRepository repo;

    public EnrollmentServiceImpl(EnrollmentRepository repo) {
        this.repo = repo;
    }

    public List<Enrollment> getAllEnrollments() {
        return repo.findAll();
    }

    public Enrollment getEnrollmentById(String id) {
        return repo.findById(id).orElse(null);
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return repo.save(enrollment);
    }

    public void deleteEnrollment(String id) {
        repo.deleteById(id);
    }

}
