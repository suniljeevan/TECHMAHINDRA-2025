package com.org.TMS.service;

import com.org.TMS.model.Bus;
import com.org.TMS.model.Route;
import com.org.TMS.model.Student;
import com.org.TMS.repository.BusRepository;
import com.org.TMS.repository.RouteRepository;
import com.org.TMS.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final BusRepository busRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, BusRepository busRepository, StudentRepository studentRepository) {
        this.routeRepository = routeRepository;
        this.busRepository = busRepository;
        this.studentRepository = studentRepository;
    }
    @Override
    public List<Bus> findBusesByStudentLocation(Student student) {
        String studentLocation = student.getLocation();  // Assuming `getLocation()` returns the student's location
        return busRepository.findAll().stream()
                .filter(bus -> bus.getLocation1().equals(studentLocation) ||
                               bus.getLocation2().equals(studentLocation) ||
                               (bus.getLocation3() != null && bus.getLocation3().equals(studentLocation)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Bus> findBusesByLocation(String location) {
        return busRepository.findAll().stream()
                .filter(bus -> bus.getLocation1().equals(location) || 
                               bus.getLocation2().equals(location) || 
                               (bus.getLocation3() != null && bus.getLocation3().equals(location)))
                .collect(Collectors.toList());
    }

    @Override
    public void saveRoute(Student student, Bus bus) {
        Route route = new Route(student, bus);
        routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route getRouteById(Long id) {
        return routeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }
}
