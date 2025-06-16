package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Application;
import com.project.model.Student;
import com.project.repository.SubmittedApplicationRepository;
import com.project.service.SubmittedApplicationService;

@Service
public class SubmittedApplicationServiceImpl implements SubmittedApplicationService {

    @Autowired
    private SubmittedApplicationRepository repository;

    public List<Application> getApplicationsByStudent(Student student) {
        return repository.findByStudent(student);
    }
}
