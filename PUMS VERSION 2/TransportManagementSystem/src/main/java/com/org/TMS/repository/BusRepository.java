package com.org.TMS.repository;

import com.org.TMS.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//BusRepository.java
@Repository
public interface BusRepository extends JpaRepository<Bus, String> {
 boolean existsByBusNo(String busNo);
 Bus findByBusNo(String busNo); // Add this method
}
