package com.ums.SERVICE;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ums.MODEL.Student;
import com.ums.REPOSITORY.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudentById(long id) {
        return repo.findById(id).orElse(null);
    }

    public Student saveStudent(Student student) {
        return repo.save(student);
    }

    public void deleteStudent(long id) {
        repo.deleteById(id);
    }
}
