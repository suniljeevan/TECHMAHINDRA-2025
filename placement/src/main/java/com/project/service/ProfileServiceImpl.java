package com.project.service;

import com.project.model.Profile;
import com.project.model.Student;
import com.project.repository.ProfileRepository;
import com.project.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile getProfileByStudent(Student student) {
        return profileRepository.findByStudent(student);
    }

    @Override
    public Profile saveOrUpdateProfile(Profile profile) {
        return profileRepository.save(profile);
    }
}
