package com.org.TMS.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class BusSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_bus_no", referencedColumnName = "bus_no")
    private Bus bus; // Assuming you have a Bus entity

    @ElementCollection
    @CollectionTable(name = "bus_schedule_days", joinColumns = @JoinColumn(name = "bus_schedule_id"))
    @Column(name = "day")
    private List<String> daysRunning;

    @ElementCollection
    @CollectionTable(name = "bus_schedule_times", joinColumns = @JoinColumn(name = "bus_schedule_id"))
    @Column(name = "time")
    private List<String> departureTimes; // Store as String in format "HH:mm"

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public List<String> getDaysRunning() {
        return daysRunning;
    }

    public void setDaysRunning(List<String> daysRunning) {
        this.daysRunning = daysRunning;
    }

    public List<String> getDepartureTimes() {
        return departureTimes;
    }

    public void setDepartureTimes(List<String> departureTimes) {
        this.departureTimes = departureTimes;
    }
}
