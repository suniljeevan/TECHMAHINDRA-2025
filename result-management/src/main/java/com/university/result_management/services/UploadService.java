package com.university.result_management.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.university.result_management.models.Course;
import com.university.result_management.models.CourseEnrollment;
import com.university.result_management.models.Department;
import com.university.result_management.models.Instructor;
import com.university.result_management.models.Result;
import com.university.result_management.models.Student;
import com.university.result_management.repositories.CourseEnrollmentRepository;
import com.university.result_management.repositories.CourseRepository;
import com.university.result_management.repositories.DepartmentRepository;
import com.university.result_management.repositories.InstructorRepository;
import com.university.result_management.repositories.ResultRepository;
import com.university.result_management.repositories.StudentRepository;

@Service
public class UploadService {

    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseEnrollmentRepository courseEnrollmentRepository;
    @Autowired
    private CourseEnrollmentService courseEnrollmentService;
    
    @Autowired
    private ResultService resultService;
    @Autowired
    private CourseService courseService;  
   
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private ResultRepository   resultRepository;
  
    public List<Student> parseStudents(MultipartFile file) throws Exception {
        List<Student> students = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                String roll = getString(row.getCell(1));
             


             
                if (studentRepository.existsByRollNumber(roll)) {
                   continue;
                }

                Student student = new Student();
                student.setName(getString(row.getCell(0)));
                
                student.setRollNumber(roll);
                student.setEmail(getString(row.getCell(2)));
                String studentPassword=getString(row.getCell(3));
                student.setPassword(passwordEncoder.encode(studentPassword));
                student.setYear((int) row.getCell(4).getNumericCellValue());
                student.setSemester((int) row.getCell(5).getNumericCellValue());

                String deptName = getString(row.getCell(6));
                Department dept = findOrCreateDepartment(deptName);
                student.setDepartment(dept);

                students.add(student);
            }
        }

        return students;
    }


    
    public List<Instructor> parseInstructors(MultipartFile file) throws Exception {
        List<Instructor> instructors = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                String email = getString(row.getCell(1));

                // Skip if Instructor already exists by email
                if (instructorRepository.existsByEmail(email)) {
                    continue; // Skip this row
                }

                Instructor instructor = new Instructor();
                instructor.setName(getString(row.getCell(0)));
                instructor.setEmail(email);
                String instPass=getString(row.getCell(2));
                instructor.setPassword(passwordEncoder.encode(instPass));

                String deptName = getString(row.getCell(3));
                Department dept = findOrCreateDepartment(deptName);
                instructor.setDepartment(dept);

                instructors.add(instructor);
            }
        }

        return instructors;
    }

    public List<Department> parseDepartments(MultipartFile file) throws Exception {
        List<Department> departments = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                String deptName = getString(row.getCell(0));

                // Skip if Department already exists by name
                if (departmentRepository.existsByName(deptName)) {
                    continue; // Skip this row
                }

                Department department = new Department();
                department.setName(deptName);

                departments.add(department);
            }
        }

        return departments;
    }

    public List<Course> parseCourses(MultipartFile file) throws Exception {
        List<Course> courses = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                String code = getString(row.getCell(1));

                // Skip if Course already exists by course code
                if (courseRepository.existsByCode(code)) {
                    continue;
                }

                String name = getString(row.getCell(0));
                int credits = Integer.parseInt(getString(row.getCell(2)));
                String courseType = getString(row.getCell(3));
                int courseMarks = Integer.parseInt(getString(row.getCell(4)));

                // Validate courseMarks
                if (courseMarks != 100.0 && courseMarks != 200.0) {
                    System.out.println("Skipping course '" + name + "' due to invalid marks: " + courseMarks);
                    continue;
                }

                String deptName = getString(row.getCell(5));
                Department dept = findOrCreateDepartment(deptName);
                System.out.println(deptName);

                String instructorEmail = getString(row.getCell(6));
                Instructor instructor = findOrCreateInstructor(instructorEmail);

                Course course = Course.builder()
                    .name(name)
                    .code(code)
                    .credits(credits)
                    .course_type(courseType)
                    .courseMarks(courseMarks)
                    .department(dept)
                    .instructor(instructor)
                    .build();

                courses.add(course);
            }
        }

        return courses;
    }


    private String getString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> new BigDecimal(cell.getNumericCellValue()).toPlainString();
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula().trim();
            default -> "";
        };
    }


    private Department findOrCreateDepartment(String name) {
        return departmentRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Department not found: " + name));
    }
    private Instructor findOrCreateInstructor(String email) {
        return instructorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Instructor not found: " + email));
    }
    public String processAllMarks(MultipartFile file, Long courseId, Model model) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;

            String rollNo = row.getCell(0).getStringCellValue().trim();
            Double caMark = row.getCell(1).getNumericCellValue();
            Double midMark = row.getCell(2).getNumericCellValue();
            Double endMark = row.getCell(3).getNumericCellValue();

            Student student = studentRepository.findByRollNumber(rollNo)
                    .orElse(null);

            if (student == null) continue;

            CourseEnrollment enrollment = courseEnrollmentRepository
                    .findByStudentAndCourse(student, course)
                    .orElse(null);

            if (enrollment == null || enrollment.getStatus() == null) continue;

            // Assign marks with progression
            enrollment.setCaMarks(caMark);
            enrollment.setMidtermMarks(midMark);

            if ("Midterm Completed".equals(enrollment.getStatus())) {
                enrollment.setEndtermMarks(endMark);
                double totalMarks = caMark + midMark + endMark;
                enrollment.setTotalMarksObtained(totalMarks);
                enrollment.setGrade(resultService.calculateGrade(totalMarks));
                enrollment.setStatus(resultService.calculateStatus(totalMarks));

                Result result = new Result();
                result.setStudent(student);
                result.setCourse(course);
                result.setSemester(student.getSemester());
                result.setRemarks(resultService.calculateRemarks(totalMarks));
                resultService.saveResultWithCGPA(result, enrollment);
            } else {
                enrollment.setStatus("Midterm Completed");
            }

            courseEnrollmentRepository.save(enrollment);
        }

        workbook.close();
        model.addAttribute("title", "Success");
        model.addAttribute("message", "Marks uploaded successfully.");
        model.addAttribute("backUrl", "/instructor/course/" + courseId + "/students");
        return "html/common/message";
    }
    public void saveUploadedMarks(Long courseId, List<Map<String, Object>> data) {
        Course course = courseService.getOneCourse(courseId);

        for (Map<String, Object> row : data) {
            String rollNo = (String) row.get("rollNo");
            Double ca = (Double) row.get("caMarks");
            Double mid = (Double) row.get("midtermMarks");
            Double end = (Double) row.get("endtermMarks");

            Student student = studentService.findByRollno(rollNo);
            int semester = student.getSemester(); // ✅ get semester from student

            CourseEnrollment enrollment = courseEnrollmentService.getEnrollmentByStudentAndCourse(student.getId(), courseId);

            // Update enrollment
            enrollment.setCaMarks(ca);
            enrollment.setMidtermMarks(mid);
            enrollment.setEndtermMarks(end);

            double total = ca + mid + end;
            enrollment.setTotalMarksObtained(total);
            enrollment.setGrade(resultService.calculateGrade(total));
            enrollment.setStatus(resultService.calculateStatus(total));
            courseEnrollmentRepository.save(enrollment);

            // Create or update result
            Result result = resultService.findByStudentCourseAndSemester(student.getId(), courseId, semester)
                    .orElse(new Result());

            result.setStudent(student);
            result.setCourse(course);
            result.setCourseEnrollment(enrollment);
            result.setSemester(semester);  // ✅ set semester from student
            result.setMarks(total);
         // Calculate status and GPA for this result
            String status = resultService.calculateStatus(total);
            result.setRemarks(status);
            result.setGPA(resultService.calculateGPA(total));

            // Save the result first so it's included in semester-wide calculations
            resultRepository.save(result);

            // Now update totalCredits for all results in this semester
            resultService.updateTotalCreditsForSemester(student.getId(), result.getSemester());

        }
    }

    public List<Map<String, Object>> parseMarksToMap(MultipartFile file) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header

            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("rollNo", row.getCell(0).getStringCellValue().trim());
            rowMap.put("caMarks", row.getCell(1).getNumericCellValue());
            rowMap.put("midtermMarks", row.getCell(2).getNumericCellValue());
            rowMap.put("endtermMarks", row.getCell(3).getNumericCellValue());

            list.add(rowMap);
        }

        workbook.close();
        return list;
    }








}
