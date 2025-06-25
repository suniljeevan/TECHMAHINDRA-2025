package com.org.TMS.service;

import com.org.TMS.model.Student;

public interface RegisterService {
    boolean isStudentRegistered(String sid);
    void saveStudent(Student student);
}
