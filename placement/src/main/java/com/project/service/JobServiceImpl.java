package com.project.service;

import com.project.model.Application;
import com.project.model.Job;
import com.project.repository.ApplicationRepository;
import com.project.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long jobId) {
        return jobRepository.findById(jobId).orElse(null);
    }

    @Override
    public void applyForJob(Long jobId, Application application) {
        Job job = getJobById(jobId);
        if (job != null) {
            application.setJob(job); // Link application to job
            applicationRepository.save(application);
        }
    }
}
