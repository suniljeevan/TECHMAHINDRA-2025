package com.org.TMS.service;

import com.org.TMS.model.Bus;
import com.org.TMS.model.BusSchedule;
import com.org.TMS.repository.BusScheduleRepository;
import com.org.TMS.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusScheduleService {

    @Autowired
    private BusScheduleRepository busScheduleRepository;

    @Autowired
    private BusRepository busRepository;

    public void addBusSchedule(BusSchedule busSchedule) {
        busScheduleRepository.save(busSchedule);
    }

    public List<BusSchedule> getAllBusSchedules() {
        return busScheduleRepository.findAll();
    }

    public Bus getBusByBusNo(String busNo) {
        return busRepository.findById(busNo).orElse(null);
    }
}
