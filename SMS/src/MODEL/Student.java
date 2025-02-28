package MODEL;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Comparable<Student>,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sid;
	private String sname;
	private String email;
	private String address;
	private int year;
	//alt shift s
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, email, sid, sname, year);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(sid, other.sid) && Objects.equals(sname, other.sname) && year == other.year;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", email=" + email + ", address=" + address + ", year="
				+ year + "]";
	}
	public Student(String sid, String sname, String email, String address, int year) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.email = email;
		this.address = address;
		this.year = year;
	}
	public Student() {
		
	}
	public int compareTo(Student s) {
		return sid.compareTo(s.getSid());
	}

}



