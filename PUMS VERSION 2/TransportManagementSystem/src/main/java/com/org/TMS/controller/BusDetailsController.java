package com.org.TMS.controller;

import com.org.TMS.model.Bus;
import com.org.TMS.service.BusDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/searchBusDetails")
public class BusDetailsController {

    private final BusDetailsService busDetailsService;

    @Autowired
    public BusDetailsController(BusDetailsService busDetailsService) {
        this.busDetailsService = busDetailsService;
    }

    @GetMapping
    public String showBusDetailsPage(Model model) {
        List<Bus> buses = busDetailsService.getAllBuses();
        model.addAttribute("buses", buses);
        return "busDetails";  // Display the list of buses
    }

    @GetMapping("/busDetails/{busNumber}")
    public String getBusDetails(@PathVariable String busNumber, Model model) {
        Bus bus = busDetailsService.getBusDetails(busNumber);
        int totalStudents = busDetailsService.getStudentCountForBus(busNumber);
        model.addAttribute("bus", bus);
        model.addAttribute("totalStudents", totalStudents);
        return "busDetailsCard";  // Render the bus details page
    }
}
