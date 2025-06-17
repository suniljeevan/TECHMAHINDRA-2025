package com.university.result_management.services;

import com.university.result_management.models.SystemSetting;
import com.university.result_management.repositories.SystemSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemSettingService {

    private final SystemSettingRepository repo;

    public SystemSetting getSettings() {
        return repo.findById(1L).orElseGet(() -> {
            SystemSetting setting = new SystemSetting();
            return repo.save(setting);
        });
    }

    public void updateSettings(boolean enroll, boolean assign) {
        SystemSetting setting = getSettings();
        setting.setEnrollCourseEnabled(enroll);
        setting.setAssignMarksEnabled(assign);
        repo.save(setting);
    }

}
