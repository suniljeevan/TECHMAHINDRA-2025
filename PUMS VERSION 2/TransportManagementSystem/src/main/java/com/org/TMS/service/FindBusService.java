package com.org.TMS.service;

import com.org.TMS.model.BusSchedule;
import com.org.TMS.model.FindBusDTO;
import com.org.TMS.repository.FindBusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class FindBusService {

    @Autowired
    private FindBusRepository findBusRepository;

    public List<BusSchedule> findBuses(FindBusDTO findBusDTO) {
        // Determine the day of the week from the given date
        DayOfWeek dayOfWeek = findBusDTO.getTravelDate().getDayOfWeek();
        String dayName = dayOfWeek.name();  // For example: "MONDAY", "TUESDAY", etc.

        // Get the buses running on that day to the destination
        return findBusRepository.findByBusLocation1OrBusLocation2OrBusLocation3AndDaysRunningContains(
                findBusDTO.getDestination(), findBusDTO.getDestination(), findBusDTO.getDestination(), dayName);
    }
}
