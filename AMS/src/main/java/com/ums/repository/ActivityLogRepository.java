package com.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ums.model.ActivityLog;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Integer> {
    List<ActivityLog> findByAdminId(int adminId);

    // Count logs between two timestamps (useful for today's actions)
    long countByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
