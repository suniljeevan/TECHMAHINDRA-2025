package com.example.naher_farhsa.exammaster_fsvo.Service;


import com.example.naher_farhsa.exammaster_fsvo.Entity.Exam;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Course;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Hall;
import com.example.naher_farhsa.exammaster_fsvo.Repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private HallAllocationService hallAllocationService;

    @Transactional
    public void addExam(Exam exam) {

        // Check if courseId is valid
        boolean courseFound = false;
        for (Course c : Course.values()) {
            if (c.name().equals(exam.getCourseId().name())) {
                courseFound = true;
                break;
            }
        }
        if (!courseFound) {
            throw new IllegalArgumentException("Invalid courseId: " + exam.getCourseId());
        }

        // Check if hallId is valid
        boolean hallFound = false;
        for (Hall h : Hall.values()) {
            if (h.name().equals(exam.getHallId().name())) {
                hallFound = true;
                break;
            }
        }
        if (!hallFound) {
            throw new IllegalArgumentException("Invalid hallId: " + exam.getHallId());
        }


        Exam addedExam = examRepository.save(exam);
        hallAllocationService.addHallAllocation(addedExam);

    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }


    @Transactional
    public void deleteByCourse(Course courseId) {
        examRepository.deleteByCourseId(courseId);
    }



   /* @Transactional
    public Exam updateExam(Exam exam) {
        Exam existingExam = examRepository.findById(exam.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + exam.getExamId()));

        boolean isConflict = examRepository.existsByHallIdAndDateAndShiftAndExamIdNot(
                exam.getHallId(), exam.getDate(), exam.getShift(), exam.getExamId());

        if (isConflict) {
            throw new IllegalArgumentException("Exam with ID: " + exam.getExamId() +
                    " is already scheduled with this hall, date, and shift.");
        }

        boolean updateHallAlloc = false;

        if (!existingExam.getHallId().equals(exam.getHallId())) {
            existingExam.setHallId(exam.getHallId());
            updateHallAlloc = true;
        }

        if (!existingExam.getDate().equals(exam.getDate())) {
            existingExam.setDate(exam.getDate());
            updateHallAlloc = true;
        }

        if (!existingExam.getShift().equals(exam.getShift())) {
            existingExam.setShift(exam.getShift());
            updateHallAlloc = true;
        }

        Exam savedExam = examRepository.save(existingExam);

        if (updateHallAlloc) {
            hallAllocationService.updateHallAllocation(savedExam);
        }

        return savedExam;
    } */

 /*   @Transactional
    public void deleteExamByCourse( examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + examId));

        hallAllocationService.deleteByExamCourseId(examId);
        examRepository.deleteById(examId);
    }*/

   /* public void deleteAllExams() {
        if (examRepository.findAll().isEmpty())
            throw new RuntimeException("No Exam Found");
        hallAllocationService.deleteAllHallAllocation();
        examRepository.deleteAll();
    }*/

    public List<Course> getAssignedCourses() {
        return examRepository.findAllAssignedCourses();
    }

    public List<Hall> getAssignedHalls() {
        List<Hall> list = new ArrayList<>();
        for (Integer integer : examRepository.findAllAssignedHalls()) {
            Hall hall = Hall.fromValue(integer);
            list.add(hall);
        }
        return list;
    }



}
