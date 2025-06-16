package com.project.service;

import java.util.List;

import com.project.model.Application;
import com.project.model.Student;

public interface SubmittedApplicationService {
    List<Application> getApplicationsByStudent(Student student);
}
