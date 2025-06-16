package com.project.service;

import com.project.model.Interview;
import com.project.repository.AdminInterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminInterviewServiceImpl implements AdminInterviewService {

    @Autowired
    private AdminInterviewRepository adminInterviewRepository;

    @Override
    public void scheduleInterview(Interview interview) {
        adminInterviewRepository.save(interview);
    }

    @Override
    public void updateInterviewStatus(Long interviewId, String status) {
        Interview interview = adminInterviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
        interview.setInterviewStatus(status);
        adminInterviewRepository.save(interview);
    }

    @Override
    public List<Interview> getAllScheduledInterviews() {
        // Assuming you want interviews with a "Scheduled" status
        return adminInterviewRepository.findByInterviewStatus("Scheduled");
    }
}
