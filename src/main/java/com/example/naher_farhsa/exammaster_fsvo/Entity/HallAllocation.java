package com.example.naher_farhsa.exammaster_fsvo.Entity;

import com.example.naher_farhsa.exammaster_fsvo.Enum.Hall;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "HallAllocations")
public class HallAllocation {

    @Id
    private String hallAllocId;  // Unique identifier for the hall allocation (e.g., H1S1, H2S2)

    @ManyToOne
    @JoinColumn(name = "examId", referencedColumnName = "examId")
    private Exam exam;  // Many-to-One relation with Exam (multiple allocations can be related to one exam)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Student student;  // Reference to Student enum (students are now defined as enums)

}
