package com.project.repository;

import com.project.model.Profile;
import com.project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByStudent(Student student);
}
