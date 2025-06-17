package com.admin.scholarship.service;


import com.admin.scholarship.model.Student;
import com.admin.scholarship.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public void updateStatus(int id, Student.Status status) {
        Student student = repo.findById(id).orElse(null);
        if (student != null) {
            student.setStatus(status);

            if (status == Student.Status.APPROVED) {
                switch (student.getScholarshiptype()) {
                    case "NSP 2025":
                        student.setAmount(75000);
                        break;
                    case "SCF 2025":
                        student.setAmount(50000);
                        break;
                    case "MERIT 2025":
                        student.setAmount(35000);
                        break;
                    default:
                        student.setAmount(0);
                }
            } else if (status == Student.Status.REJECTED) {
                student.setAmount(0);
            }

            repo.save(student);
        }
    }
    public double getTotalSanctionedAmount() {
        Double total = repo.getTotalSanctionedAmount();
        return total != null ? total : 0.0;
    }


    
    public String getStatusByName(String studentName) {
        Student student = repo.findByName(studentName);
        return (student != null) ? student.getStatus().toString() : "Student not found";
    }
    
    public Student findByName(String name) {
        return repo.findByName(name);
    }
    
    public String getStatusByEmail(String email) {
        Student student = repo.findByEmail(email);
        return (student != null) ? student.getStatus().toString() : "Student not found";
    }
    
    public Student findByEmail(String email) {
        return repo.findByEmail(email);
    }
    public int countByStatus(Student.Status status) {
        return repo.countByStatus(status);
    }
    public Map<String, Long> getScholarshipTypeCounts() {
        return repo.findAll().stream()
            .collect(Collectors.groupingBy(Student::getScholarshiptype, Collectors.counting()));
    }

    public Map<String, Long> getApprovedScholarshipTypeCounts() {
        return repo.findAll().stream()
            .filter(s -> s.getStatus() == Student.Status.APPROVED)
            .collect(Collectors.groupingBy(Student::getScholarshiptype, Collectors.counting()));
    }



}
