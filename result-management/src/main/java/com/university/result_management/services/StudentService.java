package com.university.result_management.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.university.result_management.models.CourseEnrollment;
import com.university.result_management.models.Result;
import com.university.result_management.models.Student;
import com.university.result_management.repositories.CourseEnrollmentRepository;
import com.university.result_management.repositories.ResultRepository;
import com.university.result_management.repositories.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepo ;      //initialized to null
    private final CourseEnrollmentRepository enrollmentRepo ;      //initialized to null
    private final ResultRepository resultRepo;      //initialized to null

    public Student getStudentProfile(Long id) {
        return studentRepo.findById(id).orElseThrow();
    }

    public List<CourseEnrollment> getEnrollments(Long studentId) {
        return enrollmentRepo.findByStudentId(studentId);
    }

    public List<Result> getResults(Long studentId) {
        return resultRepo.findByStudentId(studentId);
    }
    public List<Student> getAllStudents(){
    	return studentRepo.findAll();
    }
    public Student findByRollno(String roll){
    	return studentRepo.findByRollNumber(roll).
    			orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }
    public Student findById(Long roll){
    	return studentRepo.findById(roll).
    			orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }
    public void incrementAllSemesters() {
        List<Student> students = studentRepo.findAll();
        for (Student student : students) {
            int newSemester = student.getSemester() + 1;
            student.setSemester(newSemester);
            student.setYear((newSemester + 1) / 2); // E.g., sem 1→yr1, sem 3→yr2
        }
        studentRepo.saveAll(students);
        

    }

    public void decrementAllSemesters() {
        List<Student> students = studentRepo.findAll();
        for (Student student : students) {
            int currentSemester = student.getSemester();
            int newSemester = currentSemester > 1 ? currentSemester - 1 : 1;
            student.setSemester(newSemester);
            student.setYear((newSemester + 1) / 2);
        }
        studentRepo.saveAll(students);
       

    }


   
}
