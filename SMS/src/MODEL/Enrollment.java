package MODEL;

import java.util.Objects;

public class Enrollment {
	private String studid;
	private String courseid;
	public String getStudid() {
		return studid;
	}
	public void setStudid(String studid) {
		this.studid = studid;
	}
	public String getCourseid() {
		return courseid;
	}
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	@Override
	public int hashCode() {
		return Objects.hash(courseid, studid);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enrollment other = (Enrollment) obj;
		return Objects.equals(courseid, other.courseid) && Objects.equals(studid, other.studid);
	}
	@Override
	public String toString() {
		return "Enrollment [studid=" + studid + ", courseid=" + courseid + "]";
	}
	public Enrollment(String studid, String courseid) {
		this.studid = studid;
		this.courseid = courseid;
	}
	public Enrollment() {
	}
	
	
}
