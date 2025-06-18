package com.org.TMS.repository;

import com.org.TMS.model.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findBySid(String sid); // Custom method to find by sid
    void deleteBySid(String sid); // Custom delete method by sid
}
