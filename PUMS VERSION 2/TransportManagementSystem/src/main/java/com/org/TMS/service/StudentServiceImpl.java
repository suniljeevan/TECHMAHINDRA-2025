package com.org.TMS.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.org.TMS.model.Student;
import com.org.TMS.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    @Override
    public Student getStudentById(String sid) {
        return repo.findBySid(sid).orElse(null);  // Find student by sid (String)
    }

    @Override
    public Student saveStudent(Student student) {
        return repo.save(student);
    }

    @Override
    @Transactional // This ensures that the delete operation runs within a transaction
    public void deleteStudent(String sid) {
        repo.deleteBySid(sid);  // Delete student by SID
    }
}
