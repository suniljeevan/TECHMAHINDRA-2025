package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Interview;
import com.project.repository.StudentInterviewRepository;
import com.project.service.StudentInterviewService;

@Service
public class StudentInterviewServiceImpl implements StudentInterviewService {

    @Autowired
    private StudentInterviewRepository interviewRepository;

    @Override
    public List<Interview> getInterviewsByStudentId(Long studentId) {
        return interviewRepository.findByApplication_Student_Id(studentId);
    }
}
