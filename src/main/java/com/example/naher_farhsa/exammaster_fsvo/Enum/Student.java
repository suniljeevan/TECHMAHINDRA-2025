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
    S10("S10", "Kavya Desai", List.of(Course.CS102, Course.CS104, Course.CS105)),
    S11("S11", "Vihaan Desai", List.of(Course.CS102, Course.CS105, Course.CS104)),
    S12("S12", "Krishna Nair", List.of(Course.CS107, Course.CS106, Course.CS108)),
    S13("S13", "Krishna Desai", List.of(Course.CS107, Course.CS102, Course.CS106)),
    S14("S14", "Anaya Desai", List.of(Course.CS102, Course.CS101, Course.CS107)),
    S15("S15", "Vihaan Rao", List.of(Course.CS103, Course.CS102, Course.CS107)),
    S16("S16", "Shaurya Rao", List.of(Course.CS103, Course.CS101, Course.CS105)),
    S17("S17", "Riya Agarwal", List.of(Course.CS102, Course.CS106, Course.CS107)),
    S18("S18", "Krishna Patel", List.of(Course.CS105, Course.CS106, Course.CS108)),
    S19("S19", "Anaya Patel", List.of(Course.CS105, Course.CS107, Course.CS103)),
    S20("S20", "Vivaan Desai", List.of(Course.CS103, Course.CS105, Course.CS107)),
    S21("S21", "Advik Reddy", List.of(Course.CS101, Course.CS102, Course.CS104)),
    S22("S22", "Anika Iyer", List.of(Course.CS104, Course.CS107, Course.CS108)),
    S23("S23", "Kabir Singh", List.of(Course.CS103, Course.CS106, Course.CS107)),
    S24("S24", "Aanya Reddy", List.of(Course.CS101, Course.CS102, Course.CS108)),
    S25("S25", "Reyansh Kumar", List.of(Course.CS102, Course.CS104, Course.CS105)),
    S26("S26", "Nitya Singh", List.of(Course.CS101, Course.CS103, Course.CS107)),
    S27("S27", "Ira Verma", List.of(Course.CS102, Course.CS106, Course.CS108)),
    S28("S28", "Yuvraj Shah", List.of(Course.CS104, Course.CS105, Course.CS106)),
    S29("S29", "Ishita Mehra", List.of(Course.CS101, Course.CS104, Course.CS108)),
    S30("S30", "Arnav Gupta", List.of(Course.CS102, Course.CS103, Course.CS107)),
    S31("S31", "Meera Khan", List.of(Course.CS101, Course.CS106, Course.CS107)),
    S32("S32", "Zoya Malhotra", List.of(Course.CS102, Course.CS104, Course.CS106)),
    S33("S33", "Rehan Das", List.of(Course.CS103, Course.CS105, Course.CS108)),
    S34("S34", "Tanya Kapoor", List.of(Course.CS101, Course.CS102, Course.CS107)),
    S35("S35", "Sarthak Jain", List.of(Course.CS104, Course.CS106, Course.CS108)),
    S36("S36", "Vanya Mehta", List.of(Course.CS101, Course.CS103, Course.CS105)),
    S37("S37", "Devika Joshi", List.of(Course.CS102, Course.CS104, Course.CS107)),
    S38("S38", "Hridaan Roy", List.of(Course.CS103, Course.CS105, Course.CS106)),
    S39("S39", "Rudra Sen", List.of(Course.CS101, Course.CS106, Course.CS108)),
    S40("S40", "Pari Shetty", List.of(Course.CS102, Course.CS103, Course.CS104)),
    S41("S41", "Aarohi Dey", List.of(Course.CS105, Course.CS107, Course.CS108)),
    S42("S42", "Vihaan Bhatt", List.of(Course.CS101, Course.CS102, Course.CS103)),
    S43("S43", "Saanvi Lal", List.of(Course.CS104, Course.CS106, Course.CS108)),
    S44("S44", "Kiaan Sinha", List.of(Course.CS103, Course.CS105, Course.CS107)),
    S45("S45", "Prisha Saxena", List.of(Course.CS101, Course.CS104, Course.CS106)),
    S46("S46", "Ayaan Ghosh", List.of(Course.CS102, Course.CS105, Course.CS108)),
    S47("S47", "Ishaan Joshi", List.of(Course.CS101, Course.CS103, Course.CS107)),
    S48("S48", "Kiara Bhat", List.of(Course.CS102, Course.CS104, Course.CS106)),
    S49("S49", "Neel Shah", List.of(Course.CS103, Course.CS105, Course.CS108)),
    S50("S50", "Anvi Gupta", List.of(Course.CS101, Course.CS102, Course.CS107));

    private final String studentId;
    private final String studentName;
    private final List<Course> enrolledCourses;
}

