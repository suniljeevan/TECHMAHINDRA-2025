package com.org.TMS.service;

import com.org.TMS.model.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(String sid);
    Student saveStudent(Student student);
    void deleteStudent(String sid);
}
