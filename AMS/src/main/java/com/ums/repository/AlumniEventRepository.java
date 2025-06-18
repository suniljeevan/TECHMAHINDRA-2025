package com.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ums.model.AlumniEvent;

public interface AlumniEventRepository extends JpaRepository<AlumniEvent, String> {
    // Additional custom methods can be added if needed
}
