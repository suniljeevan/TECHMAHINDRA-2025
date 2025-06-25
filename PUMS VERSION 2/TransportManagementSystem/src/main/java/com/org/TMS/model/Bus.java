package com.org.TMS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "buses")
public class Bus implements Serializable, Comparable<Bus> {
    
    @Id
    @Column(name = "bus_no", nullable = false, unique = true)
    private String busNo;

    @Column(name = "location1", nullable = false)
    private String location1;

    @Column(name = "location2", nullable = false)
    private String location2;

    @Column(name = "location3", nullable = true)
    private String location3;

    @Column(name = "driver_name", nullable = false)
    private String driverName;

    @Column(name = "driver_num", nullable = false, unique = true)
    private String driverNum;

    // Constructors
    public Bus() {
        super();
    }

    public Bus(String busNo, String location1, String location2, String location3, String driverName, String driverNum) {
        this.busNo = busNo;
        this.location1 = location1;
        this.location2 = location2;
        this.location3 = location3;
        this.driverName = driverName;
        this.driverNum = driverNum;
    }

    // Getters and Setters
    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    public String getLocation3() {
        return location3;
    }

    public void setLocation3(String location3) {
        this.location3 = location3;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverNum() {
        return driverNum;
    }

    public void setDriverNum(String driverNum) {
        this.driverNum = driverNum;
    }

    // Overridden Methods
    @Override
    public int hashCode() {
        return Objects.hash(busNo, location1, location2, location3, driverName, driverNum);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Bus other = (Bus) obj;
        return Objects.equals(busNo, other.busNo);
    }

    @Override
    public String toString() {
        return "Bus [busNo=" + busNo + ", location1=" + location1 + ", location2=" + location2 
                + ", location3=" + location3 + ", driverName=" + driverName + ", driverNum=" + driverNum + "]";
    }

    @Override
    public int compareTo(Bus b) {
        return this.busNo.compareTo(b.getBusNo());
    }
}
