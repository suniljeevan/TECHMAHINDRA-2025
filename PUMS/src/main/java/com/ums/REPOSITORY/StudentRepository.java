package com.ums.REPOSITORY;

import com.ums.MODEL.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}