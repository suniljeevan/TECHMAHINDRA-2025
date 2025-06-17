package com.university.result_management.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.university.result_management.models.CourseEnrollment;
import com.university.result_management.models.Result;
import com.university.result_management.repositories.CourseEnrollmentRepository;
import com.university.result_management.repositories.ResultRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarksAssignmentService {

    private final CourseEnrollmentRepository courseEnrollmentRepository;
 
    private final ResultService resultService;


    // Assign marks for multiple students
    @Transactional
    public String assignCAMarksBulk(List<Long> enrollmentIds, List<Double> marks, Model model) {
        for (int i = 0; i < enrollmentIds.size(); i++) {
            Long enrollmentId = enrollmentIds.get(i);
            Double newMark = marks.get(i);

            CourseEnrollment enrollment = courseEnrollmentRepository.findById(enrollmentId)
                    .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));

            Double previousMark = enrollment.getCaMarks();

            if (previousMark != null && previousMark.equals(newMark)) {
                continue;
            }

            enrollment.setCaMarks(newMark);
            enrollment.setStatus("CA Completed");

            courseEnrollmentRepository.save(enrollment);
        }

        model.addAttribute("title", "Success");
        model.addAttribute("message", "CA Marks updated successfully.");
        model.addAttribute("backUrl", "/instructor/enrollments");
        return "html/common/message";
    }
    @Transactional
    public String assignMidtermMarksBulk(List<Long> enrollmentIds, List<Double> marks, Model model) {
        for (int i = 0; i < enrollmentIds.size(); i++) {
            Long enrollmentId = enrollmentIds.get(i);
            Double newMark = marks.get(i);

            CourseEnrollment enrollment = courseEnrollmentRepository.findById(enrollmentId)
                    .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));

            if (!"CA Completed".equals(enrollment.getStatus())) {
                continue; // Allow only if CA is completed
            }

            Double previousMark = enrollment.getMidtermMarks();
            if (previousMark != null && previousMark.equals(newMark)) {
                continue;
            }

            enrollment.setMidtermMarks(newMark);
            enrollment.setStatus("Midterm Completed");

            courseEnrollmentRepository.save(enrollment);
        }

        model.addAttribute("title", "Success");
        model.addAttribute("message", "Midterm Marks updated successfully.");
        model.addAttribute("backUrl", "/instructor/enrollments");
        return "html/common/message";
    }
    @Transactional
    public String assignEndtermMarksBulk(List<Long> enrollmentIds, List<Double> marks, Model model) {
        for (int i = 0; i < enrollmentIds.size(); i++) {
            Long enrollmentId = enrollmentIds.get(i);
            Double newMark = marks.get(i);

            CourseEnrollment enrollment = courseEnrollmentRepository.findById(enrollmentId)
                    .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));

            if (!"Midterm Completed".equals(enrollment.getStatus())) {
                continue; // Only allow if midterm is completed
            }

            Double previousMark = enrollment.getEndtermMarks();
            if (previousMark != null && previousMark.equals(newMark)) {
                continue;
            }

            enrollment.setEndtermMarks(newMark);

            double totalMarks = enrollment.getCaMarks() + enrollment.getMidtermMarks() + newMark;
            enrollment.setTotalMarksObtained(totalMarks);
            enrollment.setGrade(resultService.calculateGrade(totalMarks));
            enrollment.setStatus(resultService.calculateStatus(totalMarks));
            courseEnrollmentRepository.save(enrollment);

            Result result = new Result();
            result.setStudent(enrollment.getStudent());
            result.setCourse(enrollment.getCourse());
            result.setSemester(enrollment.getStudent().getSemester());
            result.setRemarks(resultService.calculateRemarks(totalMarks));

            resultService.saveResultWithCGPA(result, enrollment);
        }

        model.addAttribute("title", "Success");
        model.addAttribute("message", "Endterm Marks updated successfully.");
        model.addAttribute("backUrl", "/instructor/enrollments");
        return "html/common/message";
    }







   

   
}
