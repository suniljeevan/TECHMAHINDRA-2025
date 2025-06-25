package com.org.TMS.controller;

import com.org.TMS.model.Bus;
import com.org.TMS.model.Route;
import com.org.TMS.model.Student;
import com.org.TMS.service.RouteService;
import com.org.TMS.service.StudentService;
import com.org.TMS.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private BusService busService;

    @GetMapping("")
    public String listRoutes(Model model) {
        model.addAttribute("routes", routeService.getAllRoutes());
        return "/list-routes";
    }

    @GetMapping("/add")
    public String showAddRouteForm(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("buses", busService.getAllBuses());
        return "/add-route";
    }

    @PostMapping("/addRoute")
    public String addRoute(@RequestParam String studentId, @RequestParam String busNo) {
        Student student = studentService.getStudentById(studentId);
        Bus bus = busService.getBusByNo(busNo);

        if (student != null && bus != null) {
            routeService.saveRoute(student, bus);
        }
        return "redirect:/routes";
    }

    @GetMapping("/edit/{id}")
    public String showEditRouteForm(@PathVariable Long id, Model model) {
        model.addAttribute("route", routeService.getRouteById(id));
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("buses", busService.getAllBuses());
        return "/edit-route";
    }

    @PostMapping("/updateRoute")
    public String updateRoute(@RequestParam Long id, @RequestParam String studentId, @RequestParam String busNo) {
        Route route = routeService.getRouteById(id);
        if (route != null) {
            Student student = studentService.getStudentById(studentId);
            Bus bus = busService.getBusByNo(busNo);
            route.setStudent(student);
            route.setBus(bus);
            routeService.saveRoute(route);
        }
        return "redirect:/routes";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return "redirect:/routes";
    }
    @GetMapping("/findBuses")
    @ResponseBody
    public List<Bus> findBuses(@RequestParam String studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            return routeService.findBusesByStudentLocation(student);
        }
        return new ArrayList<>();  // Return an empty list if the student is not found
    }
}
