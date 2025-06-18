package com.org.TMS.service;

import com.org.TMS.model.Bus;
import com.org.TMS.repository.BusRepository;
import com.org.TMS.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusDetailsServiceImpl implements BusDetailsService {

    private final BusRepository busRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public BusDetailsServiceImpl(BusRepository busRepository, RouteRepository routeRepository) {
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
    }

    @Override
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    @Override
    public Bus getBusDetails(String busNumber) {
        return busRepository.findByBusNo(busNumber);
    }

    @Override
    public int getStudentCountForBus(String busNumber) {
        return routeRepository.countStudentsByBusNumber(busNumber);
    }
}
