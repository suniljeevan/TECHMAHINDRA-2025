package com.project.service;

import java.util.List;

import com.project.model.Interview;

public interface AdminInterviewService {
    void scheduleInterview(Interview interview);
    void updateInterviewStatus(Long interviewId, String status);
    List<Interview> getAllScheduledInterviews();

}
