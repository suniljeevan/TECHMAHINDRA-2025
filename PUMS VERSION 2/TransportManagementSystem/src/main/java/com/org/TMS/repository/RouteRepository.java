package com.org.TMS.repository;

import com.org.TMS.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//RouteRepository.java
@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
	@Query("SELECT COUNT(r) FROM Route r WHERE r.bus.busNo = :busNumber")
	int countStudentsByBusNumber(@Param("busNumber") String busNumber);


}
