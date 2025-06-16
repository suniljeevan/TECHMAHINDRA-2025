package com.project.service;

import com.project.model.Application;
import com.project.model.ApplicationStatus;

import java.util.List;

public interface AdminApplicationService {
    List<Application> getAllApplications();
    List<Application> getAcceptedApplications();
    Application getApplicationById(Long id);
    void updateApplicationStatus(Long id, ApplicationStatus status);
    void save(Application application);
}
