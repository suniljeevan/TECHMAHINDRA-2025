package com.example.naher_farhsa.exammaster_fsvo.DTO;

import com.example.naher_farhsa.exammaster_fsvo.Enum.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HallAllocationDTO {

    private String hallAllocId;
    private LocalDate examDate;
    private Shift examShift;
    private ExamInfo examInfo;
    private StudentInfo studentInfo;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExamInfo {
        private Long examId;
        private String courseCode;
        private String courseName;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StudentInfo {
        private String studentId;
        private String studentName;
    }
}

