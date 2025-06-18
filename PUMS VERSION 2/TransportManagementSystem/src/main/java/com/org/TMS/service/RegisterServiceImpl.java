package com.org.TMS.service;

import com.org.TMS.model.Student;
import com.org.TMS.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RegisterServiceImpl implements RegisterService {

    private static final Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);

    private final RegisterRepository registerRepository;

    @Autowired
    public RegisterServiceImpl(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @Override
    public boolean isStudentRegistered(String sid) {
        logger.info("Checking if student with SID: {} is already registered.", sid);
        boolean exists = registerRepository.existsBySid(sid);
        if (exists) {
            logger.info("Student with SID: {} is already registered.", sid);
        }
        return exists;
    }

    @Override
    public void saveStudent(Student student) {
        try {
            logger.info("Attempting to save student: {}", student);
            registerRepository.save(student);
            logger.info("Student saved successfully: {}", student);
        } catch (Exception e) {
            logger.error("Error while saving student: {}", student, e);
            throw new RuntimeException("Error saving student to the database", e); // Rethrow exception after logging
        }
    }
}
