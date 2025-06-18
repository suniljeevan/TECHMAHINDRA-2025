package com.ums.controller;

import com.ums.model.ActivityLog;
import com.ums.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class ActivityLogController {

    @Autowired
    private ActivityLogService logService;

    // POST: Add new activity log
    @PostMapping("/add")
    public ActivityLog addLog(@RequestBody ActivityLog log) {
        return logService.insertLog(log);
    }

    // GET: Fetch logs by admin ID
    @GetMapping("/admin/{adminId}")
    public List<ActivityLog> getLogsByAdmin(@PathVariable int adminId) {
        return logService.fetchLogsByAdmin(adminId);
    }
}
