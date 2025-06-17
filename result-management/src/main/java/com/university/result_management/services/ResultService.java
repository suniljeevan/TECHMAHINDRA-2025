package com.university.result_management.services;






import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.university.result_management.models.CourseEnrollment;
import com.university.result_management.models.Result;
import com.university.result_management.repositories.CourseEnrollmentRepository;
import com.university.result_management.repositories.ResultRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultService {
	private final ResultRepository resultRepository;
	  private final CourseEnrollmentRepository courseEnrollmentRepo;
	
	
	public List<Result> getResultsByStudentIdAndSemester(Long studentId, int semester) {
	    return resultRepository.findByStudentIdAndSemester(studentId, semester);
	}
	 public List<Result> getResultsByStudentId(Long studentId) {
	        return resultRepository.findByStudentId(studentId);
	    }
	 public Optional<Result> findByStudentCourseAndSemester(Long studentId, Long courseId, int semester) {
	        return resultRepository.findByStudentIdAndCourseIdAndSemester(studentId, courseId, semester);
	    }
	 public double calculateGPA(double marks) {
		    if (marks < 0) marks = 0;
		    if (marks > 200) marks = 200;

		    double gpa = 1.0 + (marks / 200.0) * 9.0;

		    // Round to 2 decimal places
		    return Math.round(gpa * 100.0) / 100.0;
		}

	 public void saveResultWithCGPA(Result result, CourseEnrollment courseEnrollment) {
		    double marks = courseEnrollment.getTotalMarksObtained();
		    result.setMarks(marks);
		    double gpa = calculateGPA(marks);
		    result.setGPA(gpa);

		    // Get the student's semester from the student entity
		    int semester = courseEnrollment.getStudent().getSemester(); // Access the semester from the Student entity
		 // Calculate total credits from passed courses
		    List<CourseEnrollment> allEnrollments = courseEnrollmentRepo.findByStudentId(courseEnrollment.getStudent().getId());
		    int totalCredits = allEnrollments.stream()
		        .filter(e -> "Pass".equalsIgnoreCase(e.getStatus()))
		        .mapToInt(e -> e.getCourse().getCredits())
		        .sum();
		    result.setTotalCredits(totalCredits);

		    // Calculate CGPA based on all previous results
		    List<Result> allResults = resultRepository.findByStudentId(result.getStudent().getId());
		    double cgpa = calculateCGPA(allResults);
		    result.setCgpa(cgpa);

		    result.setSemester(semester); // Set the semester for the result
		    result.setCourseEnrollment(courseEnrollment); // Link to the course enrollment
		    resultRepository.save(result); // Save the result
		}

	    // Method to calculate CGPA from all results of a student
	 public double calculateCGPA(List<Result> results) {
		    if (results == null || results.isEmpty()) {
		        return 0.0;  // or any default valid value
		    }

		    double totalGPA = 0;
		    for (Result result : results) {
		        totalGPA += result.getGPA();
		    }
		    return totalGPA / results.size();
		}



	    // Remarks based on marks
	    public String calculateRemarks(Double marks) {
	        if (marks >= 90) return "Excellent";
	        if (marks >= 80) return "Very Good";
	        if (marks >= 70) return "Good";
	        if (marks >= 60) return "Satisfactory";
	        if (marks >= 50) return "Pass";
	        return "Fail"; // Below 50 marks
	    }

	    // Grade calculation based on marks
	    public String calculateGrade(Double marks) {
	        if (marks >= 180) return "O";
	        if (marks >= 160) return "A+";
	        if (marks >= 140) return "A";
	        if (marks >= 120) return "B+";
	        if (marks >= 100) return "B";
	        if (marks >= 80)  return "C";
	        return "F";
	    }
	    public String calculateGradeRelative(Double studentMarks, List<Double> allStudentMarks) {
	        double mean = allStudentMarks.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
	        double stdDev = Math.sqrt(allStudentMarks.stream()
	                .mapToDouble(m -> Math.pow(m - mean, 2))
	                .average()
	                .orElse(0.0));

	        if (studentMarks >= mean + 1.5 * stdDev) return "O";
	        if (studentMarks >= mean + stdDev)       return "A+";
	        if (studentMarks >= mean + 0.5 * stdDev) return "A";
	        if (studentMarks >= mean)                return "B+";
	        if (studentMarks >= mean - 0.5 * stdDev) return "B";
	        if (studentMarks >= mean - stdDev)       return "C";
	        return "F";
	    }



	    // Status based on marks
	    public String calculateStatus(Double marks) {
	        return marks >= 50 ? "Pass" : "Fail"; // Simple pass/fail status
	    }
	    public Integer getMaxTotalCredits(Long studentId) {
	        Integer maxCredits = resultRepository.findMaxTotalCreditsByStudentId(studentId);
	        return maxCredits != null ? maxCredits : 0;
	    }
	    public int updateTotalCreditsForSemester(Long studentId, int semester) {
	        // Fetch all results of the student in the given semester
	        List<Result> semesterResults = resultRepository.findByStudentIdAndSemester(studentId, semester);

	        // Calculate total earned credits (only for passed courses)
	        int totalCredits = 0;
	        for (Result r : semesterResults) {
	            if ("Pass".equalsIgnoreCase(r.getRemarks())) {
	                totalCredits += r.getCourse().getCredits(); // Ensure Course is fetched or use a join
	            }
	        }

	        // Set the same totalCredits for each result in that semester
	        for (Result r : semesterResults) {
	            r.setTotalCredits(totalCredits);
	            resultRepository.save(r);
	        }

	        return totalCredits; // Return the final total semester credits
	    }





   
}
