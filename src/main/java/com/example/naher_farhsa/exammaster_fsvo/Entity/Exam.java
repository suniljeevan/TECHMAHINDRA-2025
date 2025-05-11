package com.example.naher_farhsa.exammaster_fsvo.Entity;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Course;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Hall;
import com.example.naher_farhsa.exammaster_fsvo.Enum.Shift;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "examId_seq", sequenceName = "examId_sequence", allocationSize = 1)
    private Long examId;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Course courseId;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Hall hallId;

    @Column(nullable = false, unique = true)
    private LocalDate date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Shift shift;

}
