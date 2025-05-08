package com.ums.MODEL;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;

    private String name;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "dept_id")  // Foreign key to Department
    private Department department;  // Each faculty belongs to a department

    @OneToMany(mappedBy = "faculty")
    private List<Course> coursesHandled; // List of courses handled by the faculty

    // Generate getter and setter methods

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Course> getCoursesHandled() {
        return coursesHandled;
    }

    public void setCoursesHandled(List<Course> coursesHandled) {
        this.coursesHandled = coursesHandled;
    }

    public Faculty(Long facultyId, String name, String email, String phone, Department department,
                   List<Course> coursesHandled) {
        super();
        this.facultyId = facultyId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.coursesHandled = coursesHandled;
    }

    public Faculty() {
        super();
    }

    @Override
    public int hashCode() {
        return Objects.hash(coursesHandled, department, email, facultyId, name, phone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Faculty other = (Faculty) obj;
        return Objects.equals(coursesHandled, other.coursesHandled) && Objects.equals(department, other.department)
                && Objects.equals(email, other.email) && Objects.equals(facultyId, other.facultyId)
                && Objects.equals(name, other.name) && Objects.equals(phone, other.phone);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "facultyId=" + facultyId +
                ", name='" + name + '\'' +
                ", department=" + (department != null ? department.getName() : "null") +
                '}';
    }
}