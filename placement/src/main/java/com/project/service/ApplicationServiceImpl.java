package com.project.service;

import com.project.model.Application;
import com.project.model.ApplicationStatus;
import com.project.model.Job;
import com.project.model.Student;
import com.project.repository.ApplicationRepository;
import com.project.repository.JobRepository;
import com.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public void applyForJob(Application application, Student student) {
        // Check if the student has already applied for this job
        Application existingApplication = applicationRepository.findByJobAndStudent(application.getJob(), student);

        if (existingApplication != null) {
            throw new RuntimeException("You have already applied for this job.");
        }

        // Set application details
        application.setStatus(ApplicationStatus.APPLIED);  // Use the ApplicationStatus enum
        application.setStudent(student);

        // Save the application to the database
        applicationRepository.save(application);
    }
}
