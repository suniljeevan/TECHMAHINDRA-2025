package com.org.TMS.repository;

import com.org.TMS.model.BusSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<BusSchedule, Long> {
    List<BusSchedule> findByBus_BusNo(String busNo);
}
