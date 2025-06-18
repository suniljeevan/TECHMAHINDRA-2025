package com.org.TMS.controller;

import com.org.TMS.model.BusSchedule;
import com.org.TMS.model.FindBusDTO;
import com.org.TMS.service.FindBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FindBusController {

    @Autowired
    private FindBusService findBusService;

    @GetMapping("/findBus")
    public String showFindBusForm(Model model) {
        model.addAttribute("findBusDTO", new FindBusDTO());
        return "find_bus_form";
    }

    @PostMapping("/findBus")
    public String findBuses(FindBusDTO findBusDTO, Model model) {
        List<BusSchedule> buses = findBusService.findBuses(findBusDTO);
        model.addAttribute("buses", buses);
        model.addAttribute("userDestination", findBusDTO.getDestination());  // Add user destination to model
        return "bus_results"; // This will be the view for displaying bus results
    }
}
