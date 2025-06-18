package com.org.TMS.controller;

import com.org.TMS.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

@Controller
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    @GetMapping("/timeTable")
    public String showTimeTable(Model model) {
        Map<String, Map<String, String>> timetable = timeTableService.getWeeklyTimeTable();
        model.addAttribute("timetable", timetable);
        return "time_table";
    }
}
