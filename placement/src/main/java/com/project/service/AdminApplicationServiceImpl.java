package com.project.service;

import com.project.model.Application;
import com.project.model.ApplicationStatus;
import com.project.repository.AdminApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminApplicationServiceImpl implements AdminApplicationService {

    @Autowired
    private AdminApplicationRepository adminApplicationRepository;

    @Override
    public List<Application> getAllApplications() {
        return adminApplicationRepository.findAllWithJob();
    }

    @Override
    public List<Application> getAcceptedApplications() {
        return adminApplicationRepository.findAcceptedApplicationsWithJob();
    }

    @Override
    public Application getApplicationById(Long id) {
        return adminApplicationRepository.findById(id).orElse(null);
    }

    @Override
    public void updateApplicationStatus(Long id, ApplicationStatus status) {
        Application application = getApplicationById(id);
        if (application != null) {
            application.setStatus(status);
            adminApplicationRepository.save(application);
        }
    }

    @Override
    public void save(Application application) {
        adminApplicationRepository.save(application);
    }
}
