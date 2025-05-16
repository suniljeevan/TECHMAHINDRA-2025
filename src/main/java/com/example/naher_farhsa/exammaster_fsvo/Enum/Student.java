package com.example.naher_farhsa.exammaster_fsvo.Enum;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum Student {
    CIT01("CIT01", "Aarav Sharma", List.of(Course.CS101, Course.CS102, Course.CS103)),
    CCS02("CCS02", "Sheik Sadhik", List.of(Course.CS104, Course.CS105, Course.CS106)),
    CAI03("CAI03", "Diya Iyer", List.of(Course.CS101, Course.CS106, Course.CS108)),
    CSE04("CSE04", "Anaya Nair", List.of(Course.CS102, Course.CS103, Course.CS104)),
    CCS05("CCS05", "Ishaan Reddy", List.of(Course.CS105, Course.CS107, Course.CS108)),
    CAI06("CAI06", "Myra Verma", List.of(Course.CS101, Course.CS104, Course.CS103)),
    CSE07("CSE07", "Md Shahrukh", List.of(Course.CS102, Course.CS105, Course.CS106)),
    CIT08("CIT08", "Aadhya Rao", List.of(Course.CS103, Course.CS106, Course.CS108)),
    CCS09("CCS09", "Arjun Dev", List.of(Course.CS101, Course.CS103, Course.CS108)),
    CAI10("CAI10", "Kavya Desai", List.of(Course.CS102, Course.CS104, Course.CS105)),
    CSE11("CSE11", "Vihaan Desai", List.of(Course.CS102, Course.CS105, Course.CS104)),
    CIT12("CIT12", "Krishna Nair", List.of(Course.CS107, Course.CS106, Course.CS108)),
    CCS13("CCS13", "Krishna Desai", List.of(Course.CS107, Course.CS102, Course.CS106)),
    CAI14("CAI14", "Anaya Desai", List.of(Course.CS102, Course.CS101, Course.CS107)),
    CSE15("CSE15", "Vihaan Rao", List.of(Course.CS103, Course.CS102, Course.CS107)),
    CIT16("CIT16", "Shaurya Rao", List.of(Course.CS103, Course.CS101, Course.CS105)),
    CCS17("CCS17", "Riya Agarwal", List.of(Course.CS102, Course.CS106, Course.CS107)),
    CAI18("CAI18", "Krishna Patel", List.of(Course.CS105, Course.CS106, Course.CS108)),
    CSE19("CSE19", "Anaya Patel", List.of(Course.CS105, Course.CS107, Course.CS103)),
    CIT20("CIT20", "Vivaan Desai", List.of(Course.CS103, Course.CS105, Course.CS107)),
    CCS21("CCS21", "Advik Reddy", List.of(Course.CS101, Course.CS102, Course.CS104)),
    CAI22("CAI22", "Anika Iyer", List.of(Course.CS104, Course.CS107, Course.CS108)),
    CSE23("CSE23", "Kabir Singh", List.of(Course.CS103, Course.CS106, Course.CS107)),
    CIT24("CIT24", "Aanya Reddy", List.of(Course.CS101, Course.CS102, Course.CS108)),
    CCS25("CCS25", "Reyansh Kumar", List.of(Course.CS102, Course.CS104, Course.CS105)),
    CAI26("CAI26", "Nitya Singh", List.of(Course.CS101, Course.CS103, Course.CS107)),
    CSE27("CSE27", "Ira Verma", List.of(Course.CS102, Course.CS106, Course.CS108)),
    CIT28("CIT28", "Yuvraj Shah", List.of(Course.CS104, Course.CS105, Course.CS106)),
    CCS29("CCS29", "Ishita Mehra", List.of(Course.CS101, Course.CS104, Course.CS108)),
    CAI30("CAI30", "Arnav Gupta", List.of(Course.CS102, Course.CS103, Course.CS107)),
    CSE31("CSE31", "Meera Khan", List.of(Course.CS101, Course.CS106, Course.CS107)),
    CIT32("CIT32", "Zoya Malhotra", List.of(Course.CS102, Course.CS104, Course.CS106)),
    CCS33("CCS33", "Rehan Das", List.of(Course.CS103, Course.CS105, Course.CS108)),
    CAI34("CAI34", "Tanya Kapoor", List.of(Course.CS101, Course.CS102, Course.CS107)),
    CSE35("CSE35", "Sarthak Jain", List.of(Course.CS104, Course.CS106, Course.CS108)),
    CIT36("CIT36", "Vanya Mehta", List.of(Course.CS101, Course.CS103, Course.CS105)),
    CCS37("CCS37", "Devika Joshi", List.of(Course.CS102, Course.CS104, Course.CS107)),
    CAI38("CAI38", "Hridaan Roy", List.of(Course.CS103, Course.CS105, Course.CS106)),
    CSE39("CSE39", "Rudra Sen", List.of(Course.CS101, Course.CS106, Course.CS108)),
    CIT40("CIT40", "Pari Shetty", List.of(Course.CS102, Course.CS103, Course.CS104)),
    CCS41("CCS41", "Aarohi Dey", List.of(Course.CS105, Course.CS107, Course.CS108)),
    CAI42("CAI42", "Vihaan Bhatt", List.of(Course.CS101, Course.CS102, Course.CS103)),
    CSE43("CSE43", "Saanvi Lal", List.of(Course.CS104, Course.CS106, Course.CS108)),
    CIT44("CIT44", "Kiaan Sinha", List.of(Course.CS103, Course.CS105, Course.CS107)),
    CCS45("CCS45", "Prisha Saxena", List.of(Course.CS101, Course.CS104, Course.CS106)),
    CAI46("CAI46", "Ayaan Ghosh", List.of(Course.CS102, Course.CS105, Course.CS108)),
    CSE47("CSE47", "Ishaan Joshi", List.of(Course.CS101, Course.CS103, Course.CS107)),
    CIT48("CIT48", "Kiara Bhat", List.of(Course.CS102, Course.CS104, Course.CS106)),
    CCS49("CCS49", "Neel Shah", List.of(Course.CS103, Course.CS105, Course.CS108)),
    CAI50("CAI50", "Anvi Gupta", List.of(Course.CS101, Course.CS102, Course.CS107));


    private final String studentId;
    private final String studentName;
    private final List<Course> enrolledCourses;
}

