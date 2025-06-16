package com.project.service;

import java.util.List;

import com.project.model.Interview;

public interface StudentInterviewService {
    List<Interview> getInterviewsByStudentId(Long studentId);
}
