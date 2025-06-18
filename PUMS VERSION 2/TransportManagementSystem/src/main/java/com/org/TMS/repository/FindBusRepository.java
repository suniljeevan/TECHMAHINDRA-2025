package com.org.TMS.repository;

import com.org.TMS.model.BusSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FindBusRepository extends JpaRepository<BusSchedule, Long> {
    List<BusSchedule> findByBusLocation1OrBusLocation2OrBusLocation3AndDaysRunningContains(String location1, String location2, String location3, String day);
}
