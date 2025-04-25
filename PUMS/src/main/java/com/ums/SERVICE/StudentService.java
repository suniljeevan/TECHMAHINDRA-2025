package com.ums.SERVICE;

import com.ums.MODEL.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(long id);
    Student saveStudent(Student student);
    void deleteStudent(long id);
}
