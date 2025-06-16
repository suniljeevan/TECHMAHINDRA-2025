package com.project.service;

import com.project.model.Profile;
import com.project.model.Student;

public interface ProfileService {
    Profile getProfileByStudent(Student student);
    Profile saveOrUpdateProfile(Profile profile);
}
