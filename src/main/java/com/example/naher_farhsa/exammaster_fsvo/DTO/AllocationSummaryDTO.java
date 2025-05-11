package com.example.naher_farhsa.exammaster_fsvo.DTO;

import com.example.naher_farhsa.exammaster_fsvo.Enum.Hall;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllocationSummaryDTO {

    private String courseId;
    private String courseName;
    private int totalAllocations;
    private Hall hallNumber;
    private LocalDate examDate;
    private Shift examShift;
}
