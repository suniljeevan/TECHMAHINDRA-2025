package com.org.TMS.service;

import com.org.TMS.model.Bus;
import com.org.TMS.model.Route;
import com.org.TMS.model.Student;

import java.util.List;

public interface RouteService {
    List<Bus> findBusesByLocation(String location);
    void saveRoute(Student student, Bus bus);
    List<Route> getAllRoutes();
    Route saveRoute(Route route);
    Route getRouteById(Long id);
    void deleteRoute(Long id);
	List<Bus> findBusesByStudentLocation(Student student);
}
