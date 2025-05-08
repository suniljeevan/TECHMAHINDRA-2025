package com.ums.MODEL;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    private String name;
    private String hod;
    private String description;

    @OneToMany(mappedBy = "department")
    private List<Faculty> facultyList;  // List of faculty members in this department

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHod() {
        return hod;
    }

    public void setHod(String hod) {
        this.hod = hod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }

    public Department(Long deptId, String name, String hod, String description, List<Faculty> facultyList) {
        super();
        this.deptId = deptId;
        this.name = name;
        this.hod = hod;
        this.description = description;
        this.facultyList = facultyList;
    }

    public Department() {
        super();
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptId, description, hod, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Department other = (Department) obj;
        return Objects.equals(deptId, other.deptId) && Objects.equals(description, other.description)
                && Objects.equals(hod, other.hod) && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Department [deptId=" + deptId + ", name=" + name + ", hod=" + hod + ", description=" + description
                + "]";
    }
}