package com.org.TMS.service;

import com.org.TMS.model.Bus;

import java.util.List;

public interface BusDetailsService {
    List<Bus> getAllBuses();
    Bus getBusDetails(String busNumber);
    int getStudentCountForBus(String busNumber);
}
