package com.ums.MODEL;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Exam exam;  // Exam has a reference to Course

    private int marksObtained;

    // Getters and Setters
    

    public Student getStudent() {
        return student;
    }

    public Long getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
	}

	public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public int getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(int marksObtained) {
        this.marksObtained = marksObtained;
    }

    

    @Override
	public int hashCode() {
		return Objects.hash(exam,  marksObtained, evaluationId, student);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evaluation other = (Evaluation) obj;
		return Objects.equals(exam, other.exam) 
				&& marksObtained == other.marksObtained && Objects.equals(evaluationId, other.evaluationId)
				&& Objects.equals(student, other.student);
	}

	@Override
    public String toString() {
        return "Result{" +
                "resultId=" + evaluationId +
                ", student=" + (student != null ? student.getName() : "null") +
                ", exam=" + (exam != null ? exam.getExamId() : "null") +
                ", marksObtained=" + marksObtained +
                
                '}';
    }
}
