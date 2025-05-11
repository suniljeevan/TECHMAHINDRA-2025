package com.example.naher_farhsa.exammaster_fsvo.Enum;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum Student {
    S01("S01", "Aarav Sharma", List.of(Course.CS101, Course.CS102, Course.CS103)),
    S02("S02", "Sheik Sadhik", List.of(Course.CS104, Course.CS105, Course.CS106)),
    S03("S03", "Diya Iyer", List.of(Course.CS101, Course.CS106, Course.CS108)),
    S04("S04", "Anaya Nair", List.of(Course.CS102, Course.CS103, Course.CS104)),
    S05("S05", "Ishaan Reddy", List.of(Course.CS105, Course.CS107, Course.CS108)),
    S06("S06", "Myra Verma", List.of(Course.CS101, Course.CS104, Course.CS103)),
    S07("S07", "Md Shahrukh", List.of(Course.CS102, Course.CS105, Course.CS106)),
    S08("S08", "Aadhya Rao", List.of(Course.CS103, Course.CS106, Course.CS108)),
    S09("S09", "Arjun Dev", List.of(Course.CS101, Course.CS103, Course.CS108)),
    S10("S10", "Kavya Desai", List.of(Course.CS102, Course.CS104, Course.CS105));

    private final String studentId;
    private final String studentName;
    private final List<Course> enrolledCourses;
}

