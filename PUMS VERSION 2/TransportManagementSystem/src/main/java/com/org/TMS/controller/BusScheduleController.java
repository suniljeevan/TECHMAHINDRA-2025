package com.org.TMS.controller;

import com.org.TMS.model.BusSchedule;
import com.org.TMS.service.BusScheduleService;
import com.org.TMS.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BusScheduleController {

    @Autowired
    private BusScheduleService busScheduleService;

    @GetMapping("/busScheduleManagement")
    public String busScheduleManagement(Model model) {
        model.addAttribute("busSchedules", busScheduleService.getAllBusSchedules());
        return "busScheduleManagement";
    }

    @PostMapping("/addBusSchedule")
    public String addBusSchedule(
            @RequestParam("daysRunning") List<String> daysRunning,
            @RequestParam("departureTimes") List<String> departureTimes,
            @RequestParam("busNo") String busNo, 
            Model model) {

        // Fetch Bus object using busNo
        Bus bus = busScheduleService.getBusByBusNo(busNo);

        // Create a BusSchedule object
        BusSchedule busSchedule = new BusSchedule();
        busSchedule.setBus(bus);
        busSchedule.setDaysRunning(daysRunning);
        busSchedule.setDepartureTimes(departureTimes);

        // Save to the database
        busScheduleService.addBusSchedule(busSchedule);

        return "redirect:/busScheduleManagement";
    }
}
