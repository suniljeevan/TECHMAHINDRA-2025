package com.org.TMS.service;

import com.org.TMS.model.Bus;
import com.org.TMS.model.BusSchedule;
import com.org.TMS.repository.BusRepository;
import com.org.TMS.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimeTableService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private TimeTableRepository timeTableRepository;

    public Map<String, Map<String, String>> getWeeklyTimeTable() {
        List<Bus> buses = busRepository.findAll();
        Map<String, Map<String, String>> timetable = new HashMap<>();

        for (Bus bus : buses) {
            List<BusSchedule> schedules = timeTableRepository.findByBus_BusNo(bus.getBusNo());
            Map<String, String> dayStatus = new HashMap<>();

            // Initialize all days with "Not Running"
            for (String day : new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}) {
                dayStatus.put(day, "Not Running");
            }

            // Update the status for days in which the bus is running
            for (BusSchedule schedule : schedules) {
                for (String runningDay : schedule.getDaysRunning()) {
                    if (dayStatus.containsKey(runningDay)) {
                        // Display details in the format "Running: Location1 -> Location3 via Location2"
                        String details = "Running: " + bus.getLocation1();
                        if (bus.getLocation3() != null) {
                            details += " -> " + bus.getLocation3();
                        }
                        if (bus.getLocation2() != null) {
                            details += " via " + bus.getLocation2();
                        }
                        dayStatus.put(runningDay, details);
                    }
                }
            }

            timetable.put(bus.getBusNo(), dayStatus);
        }

        return timetable;
    }
}
