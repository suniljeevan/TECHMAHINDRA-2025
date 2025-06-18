package com.org.TMS.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary Key (Long)
    
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "bus_no", nullable = false, referencedColumnName = "bus_no") // Corrected mapping
    private Bus bus;
    // Constructors
    public Route() {}

    public Route(Student student, Bus bus) {
        this.student = student;
        this.bus = bus;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    // Override Methods
    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", student=" + student.getSid() +
                ", bus=" + bus.getBusNo() +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
