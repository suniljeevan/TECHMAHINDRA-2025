package com.project.service;

import com.project.model.Application;
import com.project.model.Student;

public interface ApplicationService {
    void applyForJob(Application application, Student student);
}
