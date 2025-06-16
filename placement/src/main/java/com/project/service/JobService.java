package com.project.service;

import com.project.model.Application;
import com.project.model.Job;

import java.util.List;

public interface JobService {

    List<Job> getAllJobs();

    Job getJobById(Long jobId);

    void applyForJob(Long jobId, Application application);
}
