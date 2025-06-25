package com.org.TMS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student implements Comparable<Student> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Auto-generated primary key

    @Column(nullable = false)
    private String sid;  // Manually assigned Student ID

    @Column(nullable = false)
    private String sname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private long contact;

    @Column(nullable = false)
    private String location;

    // Constructors
    public Student() {
        super();
    }

    public Student(String sid, String sname, String email, long contact, String location) {
        super();
        this.sid = sid;
        this.sname = sname;
        this.email = email;
        this.contact = contact;
        this.location = location;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Overridden Methods
    @Override
    public int hashCode() {
        return Objects.hash(id, sid, sname, email, contact, location);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return contact == other.contact &&
               Objects.equals(id, other.id) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(sname, other.sname) &&
               Objects.equals(email, other.email) &&
               Objects.equals(location, other.location);
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", sid=" + sid + ", sname=" + sname + ", email=" + email +
               ", contact=" + contact + ", location=" + location + "]";
    }

    @Override
    public int compareTo(Student s) {
        return this.sid.compareTo(s.getSid());
    }
}
