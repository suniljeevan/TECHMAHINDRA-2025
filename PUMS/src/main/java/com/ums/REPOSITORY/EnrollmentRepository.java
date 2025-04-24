package com.ums.REPOSITORY;

import com.ums.MODEL.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
}
