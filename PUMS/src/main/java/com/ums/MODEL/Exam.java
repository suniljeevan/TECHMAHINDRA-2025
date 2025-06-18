package com.ums.MODEL;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    @ManyToOne
    private Course course;

    private String examName; 
    private String date;
    private int totalMarks;

    @OneToMany(mappedBy = "exam")
    private List<Evaluation> results;

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public List<Evaluation> getResults() {
		return results;
	}

	public void setResults(List<Evaluation> results) {
		this.results = results;
	}

	public Exam(Long examId, Course course, String date, int totalMarks, List<Evaluation> results) {
		super();
		this.examId = examId;
		this.course = course;
		this.date = date;
		this.totalMarks = totalMarks;
		this.results = results;
	}

	public Exam() {
		super();
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(course, date, examId, examName, results, totalMarks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exam other = (Exam) obj;
		return Objects.equals(course, other.course) && Objects.equals(date, other.date)
				&& Objects.equals(examId, other.examId) && Objects.equals(examName, other.examName)
				&& Objects.equals(results, other.results) && totalMarks == other.totalMarks;
	}

	@Override
	public String toString() {
		return "Exam [examId=" + examId + ", course=" + course + ", examName=" + examName + ", date=" + date
				+ ", totalMarks=" + totalMarks + ", results=" + results + "]";
	}

	
}