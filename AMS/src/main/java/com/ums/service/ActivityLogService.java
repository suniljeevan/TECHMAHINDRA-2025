package com.ums.service;

import java.util.List;
import com.ums.model.ActivityLog;

public interface ActivityLogService {
    ActivityLog insertLog(ActivityLog log);
    List<ActivityLog> fetchLogsByAdmin(int adminId);
}
