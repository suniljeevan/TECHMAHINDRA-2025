package com.org.TMS.controller;

import com.org.TMS.model.Bus;
import com.org.TMS.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/buses")
public class BusController {

    private final BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public String listBuses(Model model) {
        model.addAttribute("buses", busService.getAllBuses());
        return "bus-list";
    }

    @GetMapping("/new")
    public String addBusForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "add-bus";
    }

    @PostMapping
    public String saveBus(@ModelAttribute Bus bus) {
        busService.saveBus(bus);
        return "redirect:/buses";
    }

    @GetMapping("/edit/{busNo}")
    public String editBusForm(@PathVariable String busNo, Model model) {
        Bus bus = busService.getBusByNo(busNo);
        if (bus != null) {
            model.addAttribute("bus", bus);
            return "edit-bus";
        }
        return "redirect:/buses";
    }

    @PostMapping("/{busNo}/update")
    public String updateBus(@PathVariable String busNo, @ModelAttribute Bus bus) {
        bus.setBusNo(busNo);
        busService.saveBus(bus);
        return "redirect:/buses";
    }

    @GetMapping("/{busNo}/delete")
    public String deleteBus(@PathVariable String busNo) {
        busService.deleteBus(busNo);
        return "redirect:/buses";
    }
}
