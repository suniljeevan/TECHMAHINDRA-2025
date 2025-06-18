package com.ums.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ums.model.ActivityLog;
import com.ums.repository.ActivityLogRepository;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    @Autowired
    private ActivityLogRepository logRepo;

    @Override
    public ActivityLog insertLog(ActivityLog log) {
        return logRepo.save(log);
    }

    @Override
    public List<ActivityLog> fetchLogsByAdmin(int adminId) {
        return logRepo.findByAdminId(adminId);
    }
}
