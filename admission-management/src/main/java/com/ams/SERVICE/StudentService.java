package com.ams.SERVICE;

import com.ams.MODEL.*;
import com.ams.REPOSITORY.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository repo;

    private static final Map<String, String> PROGRAM_PREFIX_MAP = new HashMap<>();
    static {
        // School of Computer Science & Engineering
        PROGRAM_PREFIX_MAP.put("B.Tech in Computer Science and Engineering", "CSE");
        PROGRAM_PREFIX_MAP.put("B.Tech in Computer Science and Engineering (Artificial Intelligence and Machine Learning)", "CSEAI");
        PROGRAM_PREFIX_MAP.put("B.Tech in Computer Science and Engineering (Data Science)", "CSEDS");
        PROGRAM_PREFIX_MAP.put("B.Tech in Computer Science and Engineering (Cyber Security)", "CSECS");
        PROGRAM_PREFIX_MAP.put("B.Tech in Computer Science and Engineering (Blockchain)", "CSEBC");
        PROGRAM_PREFIX_MAP.put("B.Tech in Computer Science and Engineering (Internet of Things)", "CSEIOT");
        PROGRAM_PREFIX_MAP.put("B.Tech in Computer Science and Technology (Big Data)", "CSTBD");
        PROGRAM_PREFIX_MAP.put("B.Tech in Computer Science and Technology (DevOps)", "CSTDO");
        PROGRAM_PREFIX_MAP.put("B.Tech in Information Science and Engineering", "ISE");
        PROGRAM_PREFIX_MAP.put("B.Tech in Information Science and Technology", "IST");
        PROGRAM_PREFIX_MAP.put("B.Tech in Artificial Intelligence and Data Science", "AIDS");
        PROGRAM_PREFIX_MAP.put("B.Tech in Robotics and Artificial Intelligence", "RAI");
        PROGRAM_PREFIX_MAP.put("M.Tech in Artificial Intelligence", "MTAI");
        PROGRAM_PREFIX_MAP.put("M.Tech in Data Science", "MTDS");
        // School of Information Science
        PROGRAM_PREFIX_MAP.put("BCA (Data Science)", "BCADS");
        PROGRAM_PREFIX_MAP.put("Bachelor of Computer Applications (BCA)", "BCA");
        PROGRAM_PREFIX_MAP.put("BCA (Artificial Intelligence & Machine Learning)", "BCAAI");
        PROGRAM_PREFIX_MAP.put("MCA – Master of Computer Applications", "MCA");
        // School of Engineering
        PROGRAM_PREFIX_MAP.put("B.Tech in Civil Engineering", "CE");
        PROGRAM_PREFIX_MAP.put("B.Tech in Electrical and Electronics Engineering", "EEE");
        PROGRAM_PREFIX_MAP.put("B.Tech in Electronics and Communication Engineering", "ECE");
        PROGRAM_PREFIX_MAP.put("B.Tech in Mechanical Engineering", "ME");
        PROGRAM_PREFIX_MAP.put("B.Tech in Petroleum Engineering", "PE");
        PROGRAM_PREFIX_MAP.put("B.Tech in VLSI", "VLSI");
        PROGRAM_PREFIX_MAP.put("M.Tech in Embedded System and VLSI", "MTEV");
        PROGRAM_PREFIX_MAP.put("M.Tech in Building and Construction Technology", "MTEB");
        // School of Commerce and Economics
        PROGRAM_PREFIX_MAP.put("B.Com (Banking and Finance)", "BCBF");
        PROGRAM_PREFIX_MAP.put("B.Com (ACCA)", "BCAC");
        PROGRAM_PREFIX_MAP.put("B.Com (Corporate Accounting and Taxation)", "BCCAT");
        PROGRAM_PREFIX_MAP.put("B.Com (Business Analytics)", "BCBA");
        PROGRAM_PREFIX_MAP.put("B.Com (Corporate Accounting Integrated with CMA-US)", "BCCMA");
        // School of Law
        PROGRAM_PREFIX_MAP.put("BA LL.B (Hons.)", "BALLB");
        PROGRAM_PREFIX_MAP.put("BBA LL.B (Hons.)", "BBALLB");
        PROGRAM_PREFIX_MAP.put("L.L.M (Master of Laws)", "LLM");
        // School of Management
        PROGRAM_PREFIX_MAP.put("BBA (Aviation Management)", "BBAAV");
        PROGRAM_PREFIX_MAP.put("BBA (Business Analytics)", "BBABA");
        PROGRAM_PREFIX_MAP.put("Bachelor of Business Administration (BBA)", "BBA");
        PROGRAM_PREFIX_MAP.put("BBA (Digital Marketing)", "BBADM");
        PROGRAM_PREFIX_MAP.put("MBA Program [Dual Major Specialization]", "MBA");
        PROGRAM_PREFIX_MAP.put("MBA in Business Analytics", "MBABA");
        PROGRAM_PREFIX_MAP.put("MBA in Digital Marketing", "MBADM");
        PROGRAM_PREFIX_MAP.put("MBA in Banking and Finance Management", "MBABF");
        PROGRAM_PREFIX_MAP.put("MBA in Marketing and Finance", "MBAMF");
    }

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> findAll() {
        return (List<Student>) repo.findAll();
    }

    public List<Student> findBySchool(String school) {
        return repo.findBySchool(school);
    }

    public List<String> getAllSchools() {
        return repo.findDistinctSchools();
    }

    public Optional<Student> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public void save(Student student) {
        repo.save(student);
    }

    public void updateStatus(Long id, String status) {
        Student s = repo.findById(id).orElse(null);
        if (s != null) {
            s.setStatus(status);

            if ("Approved".equalsIgnoreCase(status)
                    && (s.getRollNumber() == null || s.getRollNumber().isEmpty())) {
                String rollNumber = generateRollNumber(s.getProgram());
                s.setRollNumber(rollNumber);
            }

            repo.save(s);
        }
    }

    // Generates a roll number like: PREFIX0001, PREFIX0002, ...
    private String generateRollNumber(String program) {
        String prefix = PROGRAM_PREFIX_MAP.getOrDefault(program, "GEN");
        long count = repo.countByProgramAndStatus(program, "Approved");
        long nextNumber = count + 1;
        return String.format("%s%04d", prefix, nextNumber);
    }
}